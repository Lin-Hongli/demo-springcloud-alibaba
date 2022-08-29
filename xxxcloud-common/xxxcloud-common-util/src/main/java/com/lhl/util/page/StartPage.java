package com.lhl.util.page;

import java.lang.annotation.*;

/**
 * @description: StartPage分页注解
 * @author: LHL
 * @create: 2022-03-31
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StartPage {
    //页号的参数名
    String pageNo() default "pageNo";
    //每页大小的参数名
    String pageSize() default "pageSize";
}
