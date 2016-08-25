package com.kongkheang.kmb.api.domain.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder to construct {@link ResponseMessage}
 * 
 * @author sayseakleng
 *
 */
public class ResponseMessageBuilder {
	
	private ResponseMessage<Object> message = new ResponseMessage<Object>();
	
	private ResponseMessageBuilder() {
		
	}
	
	/**
	 * Create an instance of ResponseMessageBuilder
	 * 
	 * <br/>
	 * @param isSuccess 
	 * 		<br/>true : create default success Response
	 * 		<br/>else : create default error Response
	 * 		
	 * @return
	 */
	public static ResponseMessageBuilder instance(boolean isSuccess) {
		if(isSuccess) {
			return success();
		}
		else {
			return fail();
		}
	}
	
	
	/**
	 * Create an instance of ResponseMessageBuilder
	 * 
	 * <br/>
	 * @param body 
	 * 		<br/>NULL : create default error Response
	 * 		<br/>Otherwise : create success Response with passing body
	 * 		
	 * @return
	 */
	public static ResponseMessageBuilder instance(Object body) {
		if(body != null) {
			ResponseMessageBuilder success = success();
			success.addBody(body);
			return success;
		}
		else {
			return fail();
		}
	}
	
	
	/**
	 * Create default success ResponseMessage
	 * <br/>
	 * ResultCode: 0000
	 * ResultMessage: success
	 * @return
	 */
	public static ResponseMessageBuilder success() {
		ResponseMessageBuilder builder = new ResponseMessageBuilder();
		builder.message.setResult(true);
		return builder;
	}
	
	/**
	 * Create default fail ResponseMessage
	 * <br/>
	 * ResultCode: SYS001
	 * ResultMessage: System Error
	 * @return
	 */
	public static ResponseMessageBuilder fail() {
		ResponseMessageBuilder builder = new ResponseMessageBuilder();
		builder.message.setResult(false);
		
		return builder;
	}
	
	
	
	/**
	 * Add ResultCode
	 * @param code
	 * @return
	 */
	public ResponseMessageBuilder addCode(ResponseCode code) {
		List<ResponseCode> resultCodes = createOrGetResultCodes();
		resultCodes.add(code);
		
		return this;
	}
	
	
	/**
	 * Internally create or get ResultCodes from ResponseMessage
	 * @return
	 */
	private List<ResponseCode> createOrGetResultCodes() {
		List<ResponseCode> resultCodes = this.message.getResultCodes();
		
		if(resultCodes == null) {
			resultCodes = new ArrayList<>();
			this.message.setResultCodes(resultCodes);
		}
		
		return resultCodes;
	}
	
	/**
	 * Add ResponseBody
	 * @param body
	 * @return
	 */
	public ResponseMessageBuilder addBody(Object body) {
		this.message.setBody(body);
		return this;
	}
	
	
	/**
	 * Finally call to construct ResponseMessage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final <T> ResponseMessage<T> build() {
		
		// check if resultCodes is empty
		List<ResponseCode> resultCodes = createOrGetResultCodes();
		if(resultCodes.isEmpty()) {
			if(this.message.isResult()) {
				addCode(ResponseCode.SUCCESS);
			}
			else {
				addCode(ResponseCode.E0001);
			}
		}
		
		return (ResponseMessage<T>) message;
	}

}
