package com.lhl.xxxcloud.service.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author LinHongli
 */
//@RefreshScope
@SpringBootApplication(scanBasePackages = {"com.lhl.xxxcloud"})
@EnableFeignClients(basePackages = {"com.lhl.xxxcloud"})
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }
}
