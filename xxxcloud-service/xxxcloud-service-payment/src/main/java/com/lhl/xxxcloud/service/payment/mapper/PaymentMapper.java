package com.lhl.xxxcloud.service.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lhl.xxxcloud.service.payment.model.entity.Payment;
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
public interface PaymentMapper extends BaseMapper<Payment> {

}
