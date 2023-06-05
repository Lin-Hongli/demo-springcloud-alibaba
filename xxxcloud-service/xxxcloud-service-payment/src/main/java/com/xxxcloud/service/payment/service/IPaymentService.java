package com.xxxcloud.service.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxcloud.common.core.result.R;
import com.xxxcloud.service.payment.model.entity.Payment;

/**
 * @author LinHongli
 */
public interface IPaymentService extends IService<Payment> {

    R<Object> create();
}
