package com.lhl.xxxcloud.service.payment.web.controller;

import com.lhl.util.result.Result;
import com.lhl.xxxcloud.service.api.order.feign.IOrderFeignClient;
import com.lhl.xxxcloud.service.api.order.model.dto.OrderDTO;
import com.lhl.xxxcloud.service.api.payment.feign.IPaymentFeignClient;
import com.lhl.xxxcloud.service.payment.service.IPaymentService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
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
    public Result<Object> test() {
        return orderFeignClient.test();
    }

    @GetMapping(value = "/create")
    public Result<Object> create() {
        return paymentService.create();
    }
}
