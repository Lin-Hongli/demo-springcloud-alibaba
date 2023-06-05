package com.xxxcloud.service.api.demo.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xxxcloud.common.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 示例数据传输对象
 * </p>
 *
 * @author LinHongli
 * @since 2023-6-1 16:17:59
 */
@Data
@ApiModel(value="DemoDto", description="示例数据传输对象")
public class DemoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账号")
    @TableField("account")
    private String account;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String nickName;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "帐号状态（0正常 1停用）")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "创建者")
    @TableField("create_by")
    private String createBy;

    @ApiModelProperty(value = "更新者")
    @TableField("update_by")
    private String updateBy;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
}
