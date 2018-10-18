package com.zzjson.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zzjson.order.utils.JsonUtil;
import com.zzjson.product.common.ProductInfoOutPut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.order.message</li>
 * <li>Version     : 1.0.0</li>
 * <li>Creation    : 2018年10月16日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Component
@Slf4j
public class ProductInfoReceiver {
    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        List<ProductInfoOutPut> productInfoOutPutList = (List<ProductInfoOutPut>) JsonUtil.fromJson(message, new TypeReference<List<ProductInfoOutPut>>() {
        });
        log.info("从队列{}接收到消息{}", "productInfo", productInfoOutPutList);
        //接收到消息存储到redis
        for (ProductInfoOutPut productInfoOutPut : productInfoOutPutList) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, String.valueOf(productInfoOutPut.getProductStock())), String.valueOf(productInfoOutPut.getProductStock()));

        }
    }
}