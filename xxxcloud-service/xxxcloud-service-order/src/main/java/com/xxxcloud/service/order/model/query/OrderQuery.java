package com.xxxcloud.service.order.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@ToString
@ApiModel(value = "OrderQuery",description = "OrderQuery描述")
public class OrderQuery{

    @ApiModelProperty("用户id")
    @NotEmpty(groups = {Remove.class,Update.class},message = "id不能为空")
    private String id;

    @NotEmpty(groups = {Update.class,UpdateByName.class},message = "name不能为空")
    private String name;
 /*   @ApiModelProperty("用户id集合")
    private List<Long> orderIdList;

    @NotNull(groups = {Get.class}, message = "orderId不能为空")
    @ApiModelProperty("用户id")
    private Long orderId;

    @Min(groups = {OrderQuery.List.class}, value = 1, message = "pageSize必须为正整数")
    private Integer pageSize;

    @Valid // 嵌套验证必须用@Valid
    @NotNull(message = "props不能为空")
    @Size(min = 1, message = "props至少要有一个自定义属性")
    private List<Order> orders;*/


    public interface Save {}
    public interface Remove {}
    public interface Update {}
    public interface UpdateByName {}
}
