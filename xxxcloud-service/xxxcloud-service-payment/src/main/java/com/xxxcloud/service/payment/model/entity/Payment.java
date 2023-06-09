package com.xxxcloud.service.payment.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xxxcloud.common.core.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author LinHongli
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("order")
public class Payment extends BaseEntity {

    private String name;
}
