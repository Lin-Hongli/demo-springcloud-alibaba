package com.lhl.xxxcloud.service.api.payment.feign;

import com.lhl.util.result.Result;
import com.lhl.xxxcloud.service.api.payment.feign.hystrix.PaymentFeignClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author LinHongli
 */
@FeignClient(value = "xxxcloud-service-payment",fallback = PaymentFeignClientHystrix.class)
public interface IPaymentFeignClient {
    //String API_PREFIX = "/api/payment";

    @GetMapping("/payment/payOrder")
    Result payOrder(@RequestParam Integer num);
}
