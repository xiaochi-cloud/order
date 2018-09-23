package com.zzjson.order.service.impl;

import com.zzjson.order.dataobject.OrderMaster;
import com.zzjson.order.dto.OrderDTO;
import com.zzjson.order.enums.OrderStatus;
import com.zzjson.order.enums.PayStatus;
import com.zzjson.order.repository.OrderDetailRepository;
import com.zzjson.order.repository.OrderMasterRepository;
import com.zzjson.order.service.OrderService;
import com.zzjson.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        //查询商品信息
        //计算总价
        //扣库存
        //生成订单
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.getUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(3));
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setOrderId(KeyUtil.getUniqueKey());
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}