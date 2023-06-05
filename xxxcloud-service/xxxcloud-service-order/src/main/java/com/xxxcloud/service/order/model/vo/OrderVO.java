package com.xxxcloud.service.order.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@ApiModel(value = "OrderVO",description = "OrderVO描述")
public class OrderVO {

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("用户id")
    private String orderId;
}
