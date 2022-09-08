package com.lhl.xxxcloud.service.api.order.feign.hystrix;

import com.lhl.util.result.Result;
import com.lhl.xxxcloud.base.emuns.ErrorCodeEnum;
import com.lhl.xxxcloud.service.api.order.feign.IDemoFeignClient;
import org.springframework.stereotype.Component;

/**
 * 服务触发熔断时的返回
 *
 * @author LinHongli
 */
@Component
public class DemoFeignClientHystrix implements IDemoFeignClient {

    @Override
    public Result<Object> demo() {
        return Result.fail(ErrorCodeEnum.B0200);
    }

}
