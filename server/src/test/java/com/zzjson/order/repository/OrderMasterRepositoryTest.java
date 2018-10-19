package com.zzjson.order.repository;

import com.zzjson.order.dataobject.OrderMaster;
import com.zzjson.order.enums.OrderStatus;
import com.zzjson.order.enums.PayStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Date;

public class OrderMasterRepositoryTest extends BaseTest {
    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("32");
        orderMaster.setBuyerName("21");
        orderMaster.setBuyerPhone("2");
        orderMaster.setBuyerAddress("33");
        orderMaster.setBuyerOpenid("321");
        orderMaster.setOrderAmount(new BigDecimal("0"));
        orderMaster.setOrderStatus(OrderStatus.CANCLE.getCode());
        orderMaster.setPayStatus(PayStatus.SUCCESS.getCode());
        orderMaster.setCreateTime(new Date(new java.util.Date().getTime()));
        orderMaster.setUpdateTime(new Date(new java.util.Date().getTime()));

        repository.save(orderMaster);
    }
}