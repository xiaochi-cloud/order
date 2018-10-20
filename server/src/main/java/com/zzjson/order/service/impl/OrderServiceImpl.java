package com.zzjson.order.service.impl;

import com.zzjson.order.dataobject.OrderDetail;
import com.zzjson.order.dataobject.OrderMaster;
import com.zzjson.order.dto.OrderDTO;
import com.zzjson.order.enums.OrderStatusEnum;
import com.zzjson.order.enums.PayStatus;
import com.zzjson.order.enums.ResultEnum;
import com.zzjson.order.exception.OrderException;
import com.zzjson.order.repository.OrderDetailRepository;
import com.zzjson.order.repository.OrderMasterRepository;
import com.zzjson.order.service.OrderService;
import com.zzjson.order.utils.KeyUtil;
import com.zzjson.product.client.ProductClient;
import com.zzjson.product.common.DecreaseStockInput;
import com.zzjson.product.common.ProductInfoOutPut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.order.service.impl</li>
 * <li>Version     : 3.8.5</li>
 * <li>Creation    : 2018年09月23日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        //查询商品信息
        List<OrderDetail> orderDetails = orderDTO.getOrderDetailList();
        List<String> productIdList = orderDetails.stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutPut> productInfos = productClient.listForOrder(productIdList);
        //读redis
        //减库存，并且把新值重新设置进redis
        //数据库订单入库异常，手动回滚redis
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        String orderId = KeyUtil.getUniqueKey();
        for (OrderDetail orderDetail : orderDetails) {
            for (ProductInfoOutPut productInfo : productInfos) {
                if (orderDetail.getProductId().equalsIgnoreCase(productInfo.getProductId())) {
                    orderAmount = productInfo.getProductPrice().multiply(BigDecimal.valueOf(orderDetail.getProductQuantity())).add(orderAmount);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());
                    //商品详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        //扣库存
        List<DecreaseStockInput> cartDTOList = orderDetails.stream().map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        //生成订单
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setOrderId(orderId);
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Transactional
    @Override
    public OrderDTO finish(String orderId) {
        //1.查询订单状态
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2.判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if (!OrderStatusEnum.NEW.getCode().equals(orderMaster.getOrderStatus())) {
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //3.修改状态为完结
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);

        //查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}