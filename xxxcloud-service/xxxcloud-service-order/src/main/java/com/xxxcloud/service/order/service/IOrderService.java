package com.xxxcloud.service.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxcloud.common.core.result.R;
import com.xxxcloud.service.order.model.entity.Order;

public interface IOrderService extends IService<Order> {

    R payOrder(Integer num);
}
