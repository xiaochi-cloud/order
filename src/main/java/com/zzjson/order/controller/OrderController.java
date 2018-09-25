package com.zzjson.order.controller;

import com.zzjson.order.VO.ResultVO;
import com.zzjson.order.client.ProductClient;
import com.zzjson.order.converter.OrderFormConvert2OrderDTO;
import com.zzjson.order.dto.OrderDTO;
import com.zzjson.order.enums.ResultEnum;
import com.zzjson.order.exception.OrderException;
import com.zzjson.order.form.OrderForm;
import com.zzjson.order.service.OrderService;
import com.zzjson.order.utils.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.order.controller</li>
 * <li>Version     : 3.8.5</li>
 * <li>Creation    : 2018年09月23日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductClient productClient;

    /**
     * 参数校验
     * 查询商品信息（调用商品服务）
     * 计算总价
     * 扣库存 （调用商品服务）
     * 订单入库
     */
    @PostMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("创建订单参数错误{},orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderFormConvert2OrderDTO.convert(orderForm);
        if (orderDTO == null) {
            log.error("[创建订单]购物车信息为空");
            throw new OrderException(ResultEnum.CAR_EMPTY);
        }
        orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderDTO.getOrderId());
        return ResultVoUtil.success(map);
    }
}