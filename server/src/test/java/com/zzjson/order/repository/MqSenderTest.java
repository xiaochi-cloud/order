package com.zzjson.order.repository;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.order.repository</li>
 * <li>Version     : 1.0.0</li>
 * <li>Creation    : 2018年09月28日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */

public class MqSenderTest extends BaseTest {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send() {
        amqpTemplate.convertAndSend("myQueue3", "now time:" + System.currentTimeMillis());
    }

    @Test
    public void sendToProduct() {
        amqpTemplate.convertAndSend("myOrder", "computer", "now time:" + System.currentTimeMillis());
    }
}