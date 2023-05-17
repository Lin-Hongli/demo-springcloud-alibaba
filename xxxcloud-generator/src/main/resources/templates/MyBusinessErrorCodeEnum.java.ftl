package ${cfg.Organization}.${cfg.ModuleName}.core;

import lombok.Getter;

/**
* <p>
* The enum BusinessErrorCodeEnum.
* </p>
*
* @author ${author}
* @since ${.now}
*/
@Getter
public enum BusinessErrorCodeEnum {
    /**
    * User 10010001 error code enum.
    */
    USER10010001("10010001", "找不到用户,id=%s"),

    /**
    * User 10010002 error code enum.
    */
    USER10010002("10010002", "xxx,id=%s"),

    USER10010003("10010003", "更新用户失败,id=%s"),

    SSO10020001("10020001", "xxxx");


    private String code;
    private String message;

    BusinessErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
