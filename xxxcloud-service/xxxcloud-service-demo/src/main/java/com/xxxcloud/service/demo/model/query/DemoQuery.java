package com.xxxcloud.service.demo.model.query;

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
@ApiModel(value = "DemoQuery",description = "DemoQuery描述")
public class DemoQuery{

    @ApiModelProperty("用户id")
    @NotEmpty(groups = {Remove.class,Update.class},message = "id不能为空")
    private String id;

    @NotEmpty(groups = {Update.class,UpdateByName.class},message = "name不能为空")
    private String name;

    public interface Save {}
    public interface Remove {}
    public interface Update {}
    public interface UpdateByName {}
}
