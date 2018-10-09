package com.zzjson.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.order.message</li>
 * <li>Version     : 1.0.0</li>
 * <li>Creation    : 2018年09月28日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Slf4j
@Component
public class MqReceiver {
    /**
     * 需要手动创建队列
     *
     * @param message
     */
    //@RabbitListener(queues = "myQueue1")
    //public void process(String message) {
    //    log.info("MqReceiver:{}", message);
    //}

    /**
     * 自动创建队列
     *
     * @param message
     */
    @RabbitListener(queuesToDeclare = {@Queue("myQueue2"), @Queue("myQueue3")})
    public void autoCreateQueue(String message) {
        log.info("autoCreateQueue:{}", message);
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue("myQueueExchange"),
            exchange = @Exchange("myExchange")
    )})
    public void exchange(String message) {
        log.info("myExchange:{}", message);
    }


    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void computerOrder(String message) {
        log.info("computerOrder:{}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("fruitOrder")
    ))
    public void fruitOrder(String message) {
        log.info("fruitOrder:{}", message);
    }
}