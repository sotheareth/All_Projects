package com.kongkheang.kmb.api.domain.response;

import java.util.List;

/**
 * Represent Response Message format
 * @author sayseakleng
 *
 * @param <T>
 */
public class ResponseMessage <T> {
	
	private boolean result;
	private List<ResponseCode> resultCodes;
	private T body;
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public List<ResponseCode> getResultCodes() {
		return resultCodes;
	}
	public void setResultCodes(List<ResponseCode> resultCodes) {
		this.resultCodes = resultCodes;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
}
