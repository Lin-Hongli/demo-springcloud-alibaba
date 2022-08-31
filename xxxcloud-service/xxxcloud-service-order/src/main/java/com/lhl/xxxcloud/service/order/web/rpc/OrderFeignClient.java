package com.lhl.xxxcloud.service.order.web.rpc;

import com.lhl.util.result.Result;
import com.lhl.xxxcloud.base.emuns.ErrorCodeEnum;
import com.lhl.xxxcloud.service.api.order.feign.IOrderFeignClient;
import com.lhl.xxxcloud.service.api.order.model.dto.OrderDTO;
import com.lhl.xxxcloud.service.order.model.entity.Order;
import com.lhl.xxxcloud.service.order.service.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 这里一般只负责调用对应的service
 * 多个service操作时，才做调用的逻辑处理
 *
 *
 * @author LinHongli
 */
@RestController
public class OrderFeignClient implements IOrderFeignClient {
    @Resource
    private IOrderService orderService;

    @Override
    public Result<Object> test() {
        int i=1/0;
        return Result.fail(ErrorCodeEnum.A0001);
    }

    @Override
    public Result<Object> create(OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        orderService.save(order);
        int i=1/0;
        return Result.fail(ErrorCodeEnum.A0001);
    }

    @Override
    public Result<Object> getOrder(Long id) {
        return Result.success(orderService.getById(id));
    }
}
