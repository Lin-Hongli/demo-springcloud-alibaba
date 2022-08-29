package com.lhl.xxxcloud.service.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lhl.util.result.Result;
import com.lhl.xxxcloud.service.order.model.entity.Order;

/**
 *
 * 为了便于排错，在 Service中的CURD方法名要与Mapper中的区别开来：
 *
 * 示例：
 * Service:get 、save 、 update、 remove
 * Mapper:select 、 insert 、update 、 delete
 *
 * @author LinHongli
 */
public interface IOrderService extends IService<Order> {

    Result payOrder(Integer num);
}
