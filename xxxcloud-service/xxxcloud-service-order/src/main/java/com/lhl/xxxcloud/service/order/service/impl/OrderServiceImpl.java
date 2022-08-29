package com.lhl.xxxcloud.service.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhl.util.result.Result;
import com.lhl.xxxcloud.service.api.payment.feign.IPaymentFeignClient;
import com.lhl.xxxcloud.service.order.mapper.OrderMapper;
import com.lhl.xxxcloud.service.order.model.entity.Order;
import com.lhl.xxxcloud.service.order.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Service 层：
 * 1)具体的业务逻辑服务编写实现，这一层的大部分方法不具有复用性，为了防止互相影响和便于后期维护和扩展，所以方法应尽量保持相互独立
 * 2)避免 Service 与 Service 进行相互调用
 *  (在开发中，我们经常会遇到 AService 中的某个业务可以提供给 BService 调用，从而让 BService 调用 AService 的方法，认为是 Service 之间具有共同的业务。
 *  其实 Service 之间没有共同的业务，而是具备通用的逻辑，这时应该将其抽离出来放在 Manager 中。)
 *
 *
 * 这里针对单表的所有逻辑处理
 *
 * @author LinHongli
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements IOrderService {

    @Resource
    private IPaymentFeignClient paymentFeignClient;

    @Override
    public Result payOrder(Integer num) {
        return paymentFeignClient.payOrder(num);
    }
}
