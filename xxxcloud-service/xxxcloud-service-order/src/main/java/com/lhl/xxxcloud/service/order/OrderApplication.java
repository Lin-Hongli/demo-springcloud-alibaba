package com.lhl.xxxcloud.service.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author LinHongli
 */
@RefreshScope
//开启OpenFeign服务调用,basePackages：feign如果不是在相同的module下，就必须加上 自己的扫描范围
@EnableFeignClients(basePackages = {"com.lhl.xxxcloud"})
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.lhl.xxxcloud"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
