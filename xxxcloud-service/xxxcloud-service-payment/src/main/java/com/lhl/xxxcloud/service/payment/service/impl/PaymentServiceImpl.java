package com.lhl.xxxcloud.service.payment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhl.util.result.Result;
import com.lhl.xxxcloud.service.api.order.feign.IOrderFeignClient;
import com.lhl.xxxcloud.service.api.order.model.dto.OrderDTO;
import com.lhl.xxxcloud.service.payment.mapper.PaymentMapper;
import com.lhl.xxxcloud.service.payment.model.entity.Payment;
import com.lhl.xxxcloud.service.payment.service.IPaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LinHongli
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {
    @Resource
    private IOrderFeignClient orderFeignClient;

    @Override
    public Result<Object> create() {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setName("323");
        return orderFeignClient.create(orderDTO);
    }
}
