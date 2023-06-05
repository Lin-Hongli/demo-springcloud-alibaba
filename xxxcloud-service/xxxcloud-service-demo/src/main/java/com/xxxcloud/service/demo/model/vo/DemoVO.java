package com.xxxcloud.service.demo.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 显示层对象，通常是 Web 向模板渲染引擎层传输的对象。
 * 现在的项目多数为前后端分离，后端只需要返回 JSON，那么可以理解为 JSON 即是需要渲染成的“模板”。
 *
 * @author LinHongli
 */
@Getter
@Setter
@ToString
@ApiModel(value = "DemoVO",description = "DemoVO描述")
public class DemoVO {
    /**
     * 对于需要使用超大整数的场景，服务端一律使用 String 字符串类型返回，禁止使用Long 类型
     */
    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("用户id")
    private String demoId;
}
