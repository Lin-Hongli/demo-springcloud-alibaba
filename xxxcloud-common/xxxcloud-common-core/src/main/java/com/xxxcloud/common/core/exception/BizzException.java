package com.xxxcloud.common.core.exception;


import com.xxxcloud.common.core.emuns.BizzErrorCodeEnum;
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
public class BizzException extends RuntimeException {

	/**
	 * 异常码
	 */
	protected String code;

	private static final long serialVersionUID = 3160241586346324994L;

	public BizzException(Throwable cause) {
		super(cause);
	}

	public BizzException(String message) {
		super(message);
	}

	public BizzException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizzException(String code, String message) {
		super(message);
		this.code = code;
	}

	public BizzException(String code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
	}

	public BizzException(BizzErrorCodeEnum codeEnum, Object... args) {
		super(String.format(codeEnum.getMessage(), args));
		this.code = codeEnum.getCode();
	}
}
