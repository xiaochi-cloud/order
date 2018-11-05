package com.zzjson.order;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = "com.zzjson.product.client")
//@EnableDiscoveryClient
//@SpringBootApplication
//@EnableCircuitBreaker
@SpringCloudApplication
@ComponentScan("com.zzjson")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
