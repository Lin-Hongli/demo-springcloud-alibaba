package com.lhl.xxxcloud.base.exception;


import com.lhl.xxxcloud.base.emuns.BusinessErrorCodeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 业务异常.
 *
 * @author LinHongli
 */
@Getter
@Setter
@NoArgsConstructor
public class BusinessException extends RuntimeException {

	/**
	 * 异常码
	 */
	protected String code;

	private static final long serialVersionUID = 3160241586346324994L;

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(String code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
	}

	public BusinessException(BusinessErrorCodeEnum codeEnum, Object... args) {
		super(String.format(codeEnum.getMessage(), args));
		this.code = codeEnum.getCode();
	}
}
