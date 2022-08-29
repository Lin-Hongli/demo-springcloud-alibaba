package com.lhl.xxxcloud.service.payment.web.controller;

import com.lhl.xxxcloud.service.payment.service.IPaymentService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LinHongli
 */
@RestController
@RequestMapping("/payemnt")
@Api("非restful风格的controller")
public class PaymentController {
    @Resource
    IPaymentService orderService;


}
