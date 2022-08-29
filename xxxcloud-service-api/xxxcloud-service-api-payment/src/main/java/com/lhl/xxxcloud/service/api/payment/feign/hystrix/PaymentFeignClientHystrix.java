package com.lhl.xxxcloud.service.api.payment.feign.hystrix;

import com.lhl.util.result.Result;
import com.lhl.xxxcloud.service.api.payment.feign.IPaymentFeignClient;
import org.springframework.stereotype.Component;

/**
 * 服务触发熔断时的返回
 *
 * @author LinHongli
 */

@Component
public class PaymentFeignClientHystrix implements IPaymentFeignClient {

    @Override
    public Result payOrder(Integer num) {
        return Result.fail("500","调用失败，触发熔断");
    }
}
