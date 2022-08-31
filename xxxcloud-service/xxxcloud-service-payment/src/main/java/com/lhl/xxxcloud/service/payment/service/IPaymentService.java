package com.lhl.xxxcloud.service.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lhl.util.result.Result;
import com.lhl.xxxcloud.service.payment.model.entity.Payment;

/**
 * @author LinHongli
 */
public interface IPaymentService extends IService<Payment> {

    Result<Object> create();
}
