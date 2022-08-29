package com.lhl.xxxcloud.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * The class Base entity.
 *
 * @author LinHongli
 */
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 2393269568666085258L;

	/**
	 * IdType.AUTOs设置id数据库自增,否则MybatisPlus会生成id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 创建人ID
	 */
	@TableField(value = "create_id")
	private Long createId;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time",fill = FieldFill.INSERT)// 新增的时候填充数据
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 修改人ID
	 */
	@TableField(value = "update_id")
	private Long updateId;

	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE) // 新增或修改的时候填充数据
	private Date updateTime;

	/**
	 * 逻辑删除字段 0未删除，1已删除
	 * TableLogic自动判断，仅操作deleted=0的数据
	 */
	@TableLogic
	private boolean deleted;


	/**
	 * mybatis-忽略实体对象的某个属性
	 * 	@Transient （javax.persistence.Transient;）或
	 * 	@TableField(exist = false)  （mybatis-plus注解）
	 */
	/*
	@Transient
	private Integer pageNum;

	@Transient
	private Integer pageSize;

	@Transient
	private String orderBy;
	*/
	/**
	 * Is new boolean.
	 *
	 * @return the boolean
	 */
	/*
	@Transient
	@JsonIgnore
	public boolean isNew() {
		return this.id == null;
	}*/
	/**
	 * Sets update info.
	 *
	 * @param user the user
	 */
	/*
	@Transient
	@JsonIgnore
	public void setUpdateInfo(LoginAuthDto user) {

		if (isNew()) {
			this.creatorId = (this.updateTime = user.getUserId());
			this.creator = user.getUserName();
			this.createdTime = (this.updateTime = new Date());
		}
		this.updateTime = user.getUserId();
		this.lastOperator = user.getUserName() == null ? user.getLoginName() : user.getUserName();
		this.updateTime = new Date();
	}*/
}
