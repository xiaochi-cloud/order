package com.zzjson.order.config;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.order.config</li>
 * <li>Version     : 3.8.5</li>
 * <li>Creation    : 2018年09月25日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@FeignClient("product")
public interface FeignConfig {
    @GetMapping("hello")
    String hello();
}