package ${cfg.Organization}.${cfg.ModuleName}.core;

import ${cfg.Organization}.${cfg.ModuleName}.core.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
* <p>
* The class Common Result.
* </p>
*
* @author ${author}
* @since ${.now}
*/
@Getter
@Setter
@ToString
public class Result<T> implements Serializable {

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
    private String message;

    /**
    * 用户消息
    */
    //    private String errorMessage;

    /**
    * 用户消息
    */
    private Date time= new Date(System.currentTimeMillis());

    /**
    * result data
    */
    private T data;

    public Result(){}

    public Result(T data){
        this.data = data;
    }

    public Result(String code,String message){
        this.code = code;
        this.message = message;
    }

    public Result(ErrorCodeEnum resultCodeEnum){
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public Result(ErrorCodeEnum resultCodeEnum, T data) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.data = data;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result(ErrorCodeEnum.SUCCESS);
        result.success = true;
        return result;
    }
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result(ErrorCodeEnum.SUCCESS,data);
        result.success = true;
        return result;
    }

    public static <T> Result<T> success(String code,String message) {
        Result<T> result = new Result(code,message);
        result.success = true;
        return result;
    }

    public static <T> Result<T> success(ErrorCodeEnum resultCodeEnum) {
        Result<T> result = new Result(resultCodeEnum);
        result.success = true;
        return result;
    }

    public static <T> Result<T> success(ErrorCodeEnum resultCodeEnum, T data) {
        Result<T> result = new Result(resultCodeEnum,data);
        result.success = true;
        return result;
    }

    public static <T> Result<T> fail(String code,String message) {
        Result<T> result = new Result(code,message);
        result.success = false;
        return result;
    }

    public static <T> Result<T> fail(ErrorCodeEnum resultCodeEnum) {
        Result<T> result = new Result(resultCodeEnum);
        result.success = false;
        return result;
    }

    public static <T> Result<T> fail(ErrorCodeEnum resultCodeEnum, T data) {
        Result<T> result = new Result(resultCodeEnum);
        result.success = false;
        return result;
    }
}

