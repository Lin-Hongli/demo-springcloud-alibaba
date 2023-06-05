package com.xxxcloud.service.api.payment.feign;

import com.xxxcloud.common.core.result.R;
import com.xxxcloud.service.api.payment.feign.hystrix.PaymentFeignClientHystrix;
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
    R payOrder(@RequestParam Integer num);
}
