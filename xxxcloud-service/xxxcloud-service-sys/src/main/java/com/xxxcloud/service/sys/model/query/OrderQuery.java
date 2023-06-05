package com.xxxcloud.service.sys.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;


/**
 * 数据查询对象，各层接收上层的查询请求。
 * 其实一般用于 Controller 接受传过来的参数，可以将其都命名为 XxxQuery
 *
 * @Validated 是对JSR-303规范的@Valid的再封装，支持分组，但不支持嵌套校验
 * 嵌套校验需要加@Valid
 *
 * @author LinHongli
 */
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
