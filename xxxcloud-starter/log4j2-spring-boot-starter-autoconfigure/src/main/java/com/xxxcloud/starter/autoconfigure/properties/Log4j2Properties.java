package com.xxxcloud.starter.autoconfigure.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "xxxcloud.log4j2")
public class Log4j2Properties {
    @Value("profile.active")
    private String active;
    private String name;
}
