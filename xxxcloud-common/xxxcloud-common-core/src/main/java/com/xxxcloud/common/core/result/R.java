package com.xxxcloud.common.core.result;

import com.xxxcloud.common.core.emuns.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用的执行结果
 *
 * @author LinHongli
 */
@Getter
@Setter
@ToString
public class R<T> implements Serializable {

    private static final long serialVersionUID = -2368446516546812379L;

    /**
     * 成功标记
     */
    private boolean success;

    /**
     * 状态码
     */
    private String code;

    /**
     * 用户消息
     */
    private String msg;

    private Date time= new Date(System.currentTimeMillis());

    /**
     * result data
     */
    private T data;

    public R(){
    }

    private R(T data){
        this.data = data;
    }

    private R(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    private R(ErrorCodeEnum resultCodeEnum){
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
    }

    private R(ErrorCodeEnum resultCodeEnum, T data) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
        this.data = data;
    }

    public static <T> R<T> ok() {
        R<T> result = new R(ErrorCodeEnum.SUCCESS);
        result.success = true;
        return result;
    }
    public static <T> R<T> ok(T data) {
        R<T> result = new R(ErrorCodeEnum.SUCCESS,data);
        result.success = true;
        return result;
    }

    public static <T> R<T> ok(String code, String msg) {
        R<T> result = new R(code,msg);
        result.success = true;
        return result;
    }

    public static <T> R<T> ok(ErrorCodeEnum resultCodeEnum) {
        R<T> result = new R(resultCodeEnum);
        result.success = true;
        return result;
    }

    public static <T> R<T> ok(ErrorCodeEnum resultCodeEnum, T data) {
        R<T> result = new R(resultCodeEnum,data);
        result.success = true;
        return result;
    }

    public static <T> R<T> fail(String msg) {
        R<T> result = new R(ErrorCodeEnum.INTERNAL.getCode(),msg);
        result.success = false;
        return result;
    }

    public static <T> R<T> fail(ErrorCodeEnum resultCodeEnum) {
        R<T> result = new R(resultCodeEnum);
        result.success = false;
        return result;
    }

    public static <T> R<T> fail(ErrorCodeEnum resultCodeEnum, String msg) {
        R<T> result = new R(resultCodeEnum.getCode(),msg);
        result.success = false;
        return result;
    }
}

