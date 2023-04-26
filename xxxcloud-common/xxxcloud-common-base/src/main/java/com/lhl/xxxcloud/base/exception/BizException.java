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
public class BizException extends RuntimeException {

	/**
	 * 异常码
	 */
	protected String code;

	private static final long serialVersionUID = 3160241586346324994L;

	public BizException(Throwable cause) {
		super(cause);
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(String code, String message) {
		super(message);
		this.code = code;
	}

	public BizException(String code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
	}

	public BizException(BusinessErrorCodeEnum codeEnum, Object... args) {
		super(String.format(codeEnum.getMessage(), args));
		this.code = codeEnum.getCode();
	}
}
