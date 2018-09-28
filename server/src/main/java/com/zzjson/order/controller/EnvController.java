package com.zzjson.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.order.controller</li>
 * <li>Version     : 1.0.0</li>
 * <li>Creation    : 2018年09月27日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@RestController
@RequestMapping("/env")
@RefreshScope
public class EnvController {
    @Value("${env}")
    public String env;

    @GetMapping("/profile")
    public String getenv() {
        return env;
    }
}