package com.xxxcloud.service.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author LinHongli
 */
//@RefreshScope
//在springcloud E版本的时候，对服务注册进行了优化，在依赖了spring-cloud-starter-alibaba-nacos-discovery之后，默认会将服务注册到注册中心。
//@EnableDiscoveryClient
//开启OpenFeign服务调用,basePackages：feign如果不是在相同的module下，就必须加上 自己的扫描范围
@EnableFeignClients(basePackages = {"com.xxxcloud"})
@SpringBootApplication(scanBasePackages = {"com.xxxcloud"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
