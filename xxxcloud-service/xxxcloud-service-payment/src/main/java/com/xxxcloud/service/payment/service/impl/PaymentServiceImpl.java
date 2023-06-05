package com.xxxcloud.service.payment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxcloud.common.core.result.R;
import com.xxxcloud.service.api.order.feign.IOrderFeignClient;
import com.xxxcloud.service.api.order.model.dto.OrderDTO;
import com.xxxcloud.service.payment.mapper.PaymentMapper;
import com.xxxcloud.service.payment.model.entity.Payment;
import com.xxxcloud.service.payment.service.IPaymentService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LinHongli
 */
@Service
@Slf4j
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {
    @Resource
    private IOrderFeignClient orderFeignClient;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public R<Object> create() {
        log.info("Seata全局事务id=================>{}", RootContext.getXID());
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setName("323");

        R<Object> result = orderFeignClient.create(orderDTO);
        if (!result.isSuccess()){
            throw new RuntimeException("账户服务异常降级了");
        }

        return R.ok();
    }
}
