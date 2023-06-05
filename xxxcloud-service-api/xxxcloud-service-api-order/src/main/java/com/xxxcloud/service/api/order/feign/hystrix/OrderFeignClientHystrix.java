package com.xxxcloud.service.api.order.feign.hystrix;

import com.xxxcloud.common.core.result.R;
import com.xxxcloud.common.core.emuns.ErrorCodeEnum;
import com.xxxcloud.service.api.order.feign.IOrderFeignClient;
import com.xxxcloud.service.api.order.model.dto.OrderDTO;
import org.springframework.stereotype.Component;

/**
 * 服务触发熔断时的返回
 *
 * @author LinHongli
 */
@Component
public class OrderFeignClientHystrix implements IOrderFeignClient {

    @Override
    public R<Object> test() {
        return R.fail(ErrorCodeEnum.B0200);
    }

    @Override
    public R<Object> getOrder(Long id) {
        return R.fail(ErrorCodeEnum.B0200);
    }

    @Override
    public R<Object> create(OrderDTO orderDTO) {
        return R.fail(ErrorCodeEnum.B0200);
    }
}
