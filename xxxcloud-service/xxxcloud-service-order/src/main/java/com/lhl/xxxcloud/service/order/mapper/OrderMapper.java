package com.lhl.xxxcloud.service.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lhl.xxxcloud.service.order.model.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * DAO 层：
 * 数据访问层，与底层 MySQL、Oracle、Hbase 等进行数据交互。
 *
 * Mapper:select 、 insert 、update 、 delete
 *
 * @author LinHongli
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
