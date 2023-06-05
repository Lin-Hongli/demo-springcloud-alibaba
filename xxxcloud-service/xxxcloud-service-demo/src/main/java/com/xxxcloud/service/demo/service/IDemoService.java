package com.xxxcloud.service.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxcloud.service.api.demo.model.entity.Demo;

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
public interface IDemoService extends IService<Demo> {

}
