package com.zzjson.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.order.controller</li>
 * <li>Version     : 3.8.5</li>
 * <li>Creation    : 2018年09月24日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@RestController
public class ClientController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/restTeaplate")
    public String restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        String s = restTemplate.getForObject("http://localhost:8083/hello", String.class);
        return s;
    }

    @RequestMapping("/loadbalance")
    public String loadBalance() {
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort() + "/hello");
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/config")
    public String config() {
        String result = restTemplate.getForObject("http://PRODUCT/hello", String.class);
        return result;
    }

}