package com.xxxcloud.service.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxcloud.service.api.demo.model.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

/**
 * DAO 层：
 * 数据访问层，与底层 MySQL、Oracle、Hbase 等进行数据交互。
 * Mapper:select 、 insert 、update 、 delete
 *
 * @author LinHongli
 */
@Mapper
public interface DemoMapper extends BaseMapper<Demo> {
}
