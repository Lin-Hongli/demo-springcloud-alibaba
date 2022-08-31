package com.lhl.xxxcloud.service.order.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author LinHongli Email:2381555134@qq.com
 * @version v1.0
 * @Description 配置MybatisPlus自动更新时间，否则插入和更新时不会自动设置时间
 * @date 2021/07/09 21:12
 */

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //属性名称，不是字段名称
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        //this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        //this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
