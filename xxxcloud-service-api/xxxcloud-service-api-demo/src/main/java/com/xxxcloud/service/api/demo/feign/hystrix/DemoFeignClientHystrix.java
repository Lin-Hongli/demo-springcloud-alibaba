package com.xxxcloud.service.api.demo.feign.hystrix;

import com.xxxcloud.common.core.result.R;
import com.xxxcloud.common.core.emuns.ErrorCodeEnum;
import com.xxxcloud.service.api.demo.feign.IDemoFeignClient;
import org.springframework.stereotype.Component;

/**
 * 服务触发熔断时的返回
 *
 * @author LinHongli
 */
@Component
public class DemoFeignClientHystrix implements IDemoFeignClient {

    @Override
    public R<Object> demo() {
        return R.fail(ErrorCodeEnum.B0200);
    }

}
