package com.xxxcloud.starter.autoconfigure.service;


import com.xxxcloud.starter.autoconfigure.properties.Log4j2Properties;

public class Log4j2Service {
    /**
     * 这里采用set方式注入属性；
     */
    private Log4j2Properties log4j2Properties;

    public Log4j2Properties getLog4j2Properties() {
        return log4j2Properties;
    }

    public void setLog4j2Properties(Log4j2Properties log4j2Properties) {
        this.log4j2Properties = log4j2Properties;
    }

    public String sayHello(String name){
        return log4j2Properties.getActive()+"-"+name+"-"+log4j2Properties.getName();
    }
}
