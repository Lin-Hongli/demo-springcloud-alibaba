package com.xxxcloud.service.order.web.rpc;

import com.xxxcloud.common.core.result.R;
import com.xxxcloud.common.core.emuns.ErrorCodeEnum;
import com.xxxcloud.service.api.order.feign.IOrderFeignClient;
import com.xxxcloud.service.api.order.model.dto.OrderDTO;
import com.xxxcloud.service.order.model.entity.Order;
import com.xxxcloud.service.order.service.IOrderService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LinHongli
 */
@RestController
@Slf4j
public class OrderFeignClient implements IOrderFeignClient {
    @Resource
    private IOrderService orderService;

    @Override
    public R<Object> test() {
        int i=1/0;
        return R.fail(ErrorCodeEnum.A0001);
    }

    @Override
    public R<Object> create(OrderDTO orderDTO) {
        log.info("Seata全局事务id=================>{}", RootContext.getXID());
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        orderService.save(order);
        int i=1/0;
        return R.ok();
    }

    @Override
    public R<Object> getOrder(Long id) {
        return R.ok(orderService.getById(id));
    }
}
