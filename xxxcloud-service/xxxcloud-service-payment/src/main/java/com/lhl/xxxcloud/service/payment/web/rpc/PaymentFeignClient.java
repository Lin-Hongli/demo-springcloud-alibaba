package com.lhl.xxxcloud.service.payment.web.rpc;

import com.lhl.util.result.Result;
import com.lhl.xxxcloud.service.api.payment.feign.IPaymentFeignClient;
import com.lhl.xxxcloud.service.payment.service.IPaymentService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 这里其他MVC注解可以不写？
 *
 * @author LinHongli
 */
//@Slf4j
//@RefreshScope
@RestController
//@RequestMapping("/payment")
@Api(value = "API - PaymentFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PaymentFeignClient implements IPaymentFeignClient {
    @Resource
    private IPaymentService orderService;

    @Override
    //@GetMapping("/payment/payOrder")
    public Result payOrder(@RequestParam Integer num) {
        int i = 1/num;
        return Result.success("支付成功");
    }
}
