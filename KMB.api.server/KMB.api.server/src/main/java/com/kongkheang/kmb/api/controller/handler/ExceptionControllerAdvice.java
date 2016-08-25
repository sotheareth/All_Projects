package com.kongkheang.kmb.api.controller.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kongkheang.kmb.api.domain.response.ResponseCode;
import com.kongkheang.kmb.api.domain.response.ResponseMessage;
import com.kongkheang.kmb.api.domain.response.ResponseMessageBuilder;

/**
 * This is class design for response message to client (can be message or view) and logging
 * <br/>
 * Handle with all controllers
 * @author sayseakleng
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	/**
	 * Handle all system Exception
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseMessage<Object> handleAllException(Exception ex) {
		logger.error(ex.getMessage(), ex);
		
		return ResponseMessageBuilder.fail()
			.build();
	}
	
	/**
	 * Handle Validation exception
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public  ResponseMessage<Object> handleValidationException(MethodArgumentNotValidException ex) {
		
		ResponseMessageBuilder builder = ResponseMessageBuilder.fail();
		
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		for(ObjectError error : allErrors) {
			String code = error.getCode();
			builder.addCode(ResponseCode.valueOf(code));
		}
				
		return builder.build();
	}
	
	
	
}