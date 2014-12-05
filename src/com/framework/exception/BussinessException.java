package com.framework.exception;

/**
 * 自定义业务异常类
 * @author 
 *
 */
public class BussinessException extends Exception {
	private String  message;
	
	
	public BussinessException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String msg) {
		this.message = msg;
	}

	/**
	 * 重写该方法取消同步
	 */
	@Override
	public Throwable fillInStackTrace() {		
		return this;
	}

	
	
}
