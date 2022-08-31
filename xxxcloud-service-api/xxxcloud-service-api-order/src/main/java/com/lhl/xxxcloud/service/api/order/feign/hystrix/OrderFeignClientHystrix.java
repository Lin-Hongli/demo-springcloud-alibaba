package com.lhl.xxxcloud.service.api.order.feign.hystrix;

import com.lhl.util.result.Result;
import com.lhl.xxxcloud.base.emuns.ErrorCodeEnum;
import com.lhl.xxxcloud.service.api.order.feign.IOrderFeignClient;
import com.lhl.xxxcloud.service.api.order.model.dto.OrderDTO;
import org.springframework.stereotype.Component;

/**
 * 服务触发熔断时的返回
 *
 * @author LinHongli
 */
@Component
public class OrderFeignClientHystrix implements IOrderFeignClient {

    @Override
    public Result<Object> test() {
        return Result.fail(ErrorCodeEnum.B0200);
    }

    @Override
    public Result<Object> getOrder(Long id) {
        return Result.fail(ErrorCodeEnum.B0200);
    }

    @Override
    public Result<Object> create(OrderDTO orderDTO) {
        return Result.fail(ErrorCodeEnum.B0200);
    }
}
