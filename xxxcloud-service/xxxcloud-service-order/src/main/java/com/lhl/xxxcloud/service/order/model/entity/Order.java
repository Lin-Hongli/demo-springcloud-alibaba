package com.lhl.xxxcloud.service.order.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lhl.xxxcloud.core.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.naming.Name;

/**
 * @Data在没有构造器的情况下，默认会添加无参构造，
 * 一旦添加了@AllArgsConstructor,@Data就不会为我们添加无参构造
 * 所以需要同时显示添加@NoArgsConstructor和@AllArgsConstructor，
 * 否则mybatis无法实例化这个实体
 * @author LinHongli
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("t_order")
public class Order extends BaseEntity {

    private String name;
}
