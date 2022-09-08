package com.lhl.xxxcloud.service.order.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置分页插件
 * @author LinHongli
 */
@Slf4j
@Configuration
public class PaginationConfig {

    /**
     * 注册mybatisPlus分页插件的拦截器，否则mybatisPlus分页查询不生效
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
        System.out.println("最大内存。。。。。。。。"+Runtime.getRuntime().maxMemory()/1024/1024);
        return new PaginationInterceptor();
    }
}
