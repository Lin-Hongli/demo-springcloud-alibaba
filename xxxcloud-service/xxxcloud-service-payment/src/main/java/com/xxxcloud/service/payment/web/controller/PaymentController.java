package com.xxxcloud.service.payment.web.controller;

import com.xxxcloud.common.core.result.R;
import com.xxxcloud.service.api.order.feign.IOrderFeignClient;
import com.xxxcloud.service.payment.service.IPaymentService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LinHongli
 */
@RestController
@RequestMapping("/payment")
@Api("非restful风格的controller")
public class PaymentController {
    @Resource
    private IPaymentService paymentService;

    @Resource
    private IOrderFeignClient orderFeignClient;

    @GetMapping(value = "/test")
    public R<Object> test() {
        return orderFeignClient.test();
    }

    @GetMapping(value = "/create")
    public R<Object> create() {
        return paymentService.create();
    }
}
