package com.lhl.xxxcloud.service.api.order.feign.hystrix;

import com.lhl.xxxcloud.base.emuns.ErrorCodeEnum;
import com.lhl.xxxcloud.service.api.order.feign.IOrderFeignClient;
import com.lhl.xxxcloud.service.api.order.model.dto.OrderDTO;
import com.lhl.util.result.Result;

/**
 * 服务触发熔断时的返回
 *
 * @author LinHongli
 */
//@Component
public class OrderFeignClientHystrix implements IOrderFeignClient {


    @Override
    public Result<Object> getOrder(Long id) {
        OrderDTO dto=new OrderDTO();

        return Result.fail(ErrorCodeEnum.B0200);
    }

    @Override
    public Result<Object> save(OrderDTO orderDTO) {
        return Result.fail(ErrorCodeEnum.B0200);
    }
}
