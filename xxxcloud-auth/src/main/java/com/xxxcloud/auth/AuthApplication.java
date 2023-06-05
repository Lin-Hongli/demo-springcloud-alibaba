package com.xxxcloud.auth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author LinHongli
 */
@EnableFeignClients(basePackages = {"com.xxxcloud.service.api.sys.feign"})
@SpringBootApplication(scanBasePackages = {"com.xxxcloud"})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
