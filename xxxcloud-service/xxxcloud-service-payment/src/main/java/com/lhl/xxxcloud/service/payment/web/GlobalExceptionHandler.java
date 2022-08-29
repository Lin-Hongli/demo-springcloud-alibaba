package com.lhl.xxxcloud.service.payment.web;


import com.lhl.xxxcloud.base.emuns.ErrorCodeEnum;
import com.lhl.xxxcloud.base.exception.BusinessException;
import com.lhl.util.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.validation.UnexpectedTypeException;
import java.util.List;

/**
 * 全局的异常拦截器
 *
 * @author LinHongli
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@Resource
	private TaskExecutor taskExecutor;
	@Value("${spring.profiles.active}")
	String profile;
	@Value("${spring.application.name}")
	String applicationName;

	/**
	 * 参数校验异常
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Result<Object> methodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("参数非法异常={}", e.getMessage(), e);
		BindingResult bindingResult = e.getBindingResult();
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			if(!CollectionUtils.isEmpty(allErrors)){
				FieldError fieldError=(FieldError) allErrors.get(0);
				return Result.fail(ErrorCodeEnum.ILLEGAL_ARGUMENT.getCode(), fieldError.getDefaultMessage());
				//return Result.fail(ErrorCodeEnum.ILLEGAL_ARGUMENT.getCode(), fieldError.getDefaultMessage()+"-->"+fieldError.getField()+"="+fieldError.getRejectedValue());
			}
		}
		return Result.fail(ErrorCodeEnum.ILLEGAL_ARGUMENT);
	}

	/**
	 * 参数类型异常
	 */
	@ExceptionHandler(UnexpectedTypeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Result<Object> unexpectedTypeException(UnexpectedTypeException e) {
		log.error("参数类型非法异常={}", e.getMessage(), e);
		return Result.fail(ErrorCodeEnum.ILLEGAL_ARGUMENT_TYPE);
	}


	/**
	 * 业务异常
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.OK)
	public Result<Object> businessException(BusinessException e) {
		log.error("业务异常={}", e.getMessage(), e);
		return Result.fail(e.getCode(), e.getMessage());
	}

	/**
	 * 全局异常.
	 * @param e the e
	 * @return the wrapper
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Result<Object> exception(Exception e) {
		log.info("保存全局异常信息 ex={}", e.getMessage(), e);
		return Result.fail(ErrorCodeEnum.INTERNAL);
	}
}
