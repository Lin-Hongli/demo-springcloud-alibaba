package ${cfg.Organization}.${cfg.ModuleName}.core;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
* <p>
* The class Base entity.
* </p>
*
* @author ${author}
* @since ${.now}
*/
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {
private static final long serialVersionUID = 2393269568666085258L;
    @TableId()
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

}
