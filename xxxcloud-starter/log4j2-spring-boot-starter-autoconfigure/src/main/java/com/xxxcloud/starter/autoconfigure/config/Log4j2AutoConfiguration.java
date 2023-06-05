package com.xxxcloud.starter.autoconfigure.config;


import com.xxxcloud.starter.autoconfigure.properties.Log4j2Properties;
import com.xxxcloud.starter.autoconfigure.service.Log4j2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration//标注该类为一个配置类
@ConditionalOnWebApplication //当前项目是Web应用时这个配置类生效
@ConditionalOnMissingBean(Log4j2Service.class)//当容器中没有配置Log4j2Service时文件才生效（没有我们封装的核心组件下面配置的组件才生效）
@EnableConfigurationProperties(Log4j2Properties.class)//开启属性文件绑定功能，Log4j2Properties自动跟配置文件绑定，同时将Log4j2Properties放到容器中
public class Log4j2AutoConfiguration {
    @Resource
    private Log4j2Properties log4j2Properties;

    @Bean
    public Log4j2Service log4j2Service(){
        Log4j2Service log4j2Service = new Log4j2Service();
        log4j2Service.setLog4j2Properties(log4j2Properties);
        return log4j2Service;
    }

}
