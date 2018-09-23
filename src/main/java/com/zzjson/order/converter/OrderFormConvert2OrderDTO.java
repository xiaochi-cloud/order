package com.zzjson.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzjson.order.dataobject.OrderDetail;
import com.zzjson.order.dto.OrderDTO;
import com.zzjson.order.enums.ResultEnum;
import com.zzjson.order.exception.OrderException;
import com.zzjson.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.order.converter</li>
 * <li>Version     : 3.8.5</li>
 * <li>Creation    : 2018年09月23日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Slf4j
public class OrderFormConvert2OrderDTO {
    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerPhone(orderForm.getPhone());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        try {
            Gson gson = new Gson();
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("[json转化错误],string ={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);

        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

}