package com.xxxcloud.service.api.payment.feign.hystrix;

import com.xxxcloud.common.core.result.R;
import com.xxxcloud.service.api.payment.feign.IPaymentFeignClient;
import org.springframework.stereotype.Component;

/**
 * 服务触发熔断时的返回
 *
 * @author LinHongli
 */

@Component
public class PaymentFeignClientHystrix implements IPaymentFeignClient {

    @Override
    public R payOrder(Integer num) {
        return R.fail("调用失败，触发熔断");
    }
}
