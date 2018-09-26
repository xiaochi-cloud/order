package com.zzjson.order.repository;

import com.zzjson.order.dataobject.OrderDetail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class OrderDetailRepositoryTest extends BaseTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123");
        orderDetail.setOrderId("321");
        orderDetail.setProductId("");
        orderDetail.setProductName("");
        orderDetail.setProductPrice(new BigDecimal("0"));
        orderDetail.setProductQuantity(0);
        orderDetail.setProductIcon("");
        orderDetailRepository.save(orderDetail);
    }
}