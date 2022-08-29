package com.lhl.xxxcloud.service.order.web.rpc;

import com.lhl.xxxcloud.service.order.service.IOrderService;
import com.lhl.xxxcloud.service.api.order.model.dto.OrderDTO;
import com.lhl.xxxcloud.service.api.order.feign.IOrderFeignClient;
import com.lhl.util.result.Result;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import javax.annotation.Resource;

/**
 * 这里一般只负责调用对应的service
 * 多个service操作时，才做调用的逻辑处理
 *
 *
 * @author LinHongli
 */
@RefreshScope
//@Component
public class OrderFeignClient implements IOrderFeignClient {
    @Resource
    private IOrderService orderService;

    @Override
    public Result<Object> getOrder(Long id) {
        return Result.success(orderService.getById(id));
    }

    @Override
    public Result<Object> save(OrderDTO orderDTO) {
        return null;
    }
}
