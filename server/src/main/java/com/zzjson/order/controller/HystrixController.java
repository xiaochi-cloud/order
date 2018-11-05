package com.zzjson.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.order.controller</li>
 * <li>Version     : 1.0.0</li>
 * <li>Creation    : 2018年10月22日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallBack")
public class HystrixController {
    //@HystrixCommand(fallbackMethod = "fallback")
    //设置超时时间
    //@HystrixCommand(commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    //})
    //设置服务熔断
    @HystrixCommand(
            ////commandKey = ""
            //commandProperties = {
            //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            //        @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            //        //统计容量阈值
            //        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            //        ////时间窗口，断路器打开，
            //        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
            //        //错误百分比
            //        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            //}
            )
    @GetMapping("/getProductInfoList")
    public String getProductInfo() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://127.0.0.1:9083/product/listForOrder", Collections.singletonList("157875196366160022"), String.class);
    }

    //private String fallback() {
    //    return "太拥挤了，请稍后再试~";
    //}
    //
    private String defaultFallBack() {
        return "默认提示：太拥挤了，请稍后重试~";
    }
}