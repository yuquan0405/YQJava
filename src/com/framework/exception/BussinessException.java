package com.framework.exception;

/**
 * �Զ���ҵ���쳣��
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
	 * ��д�÷���ȡ��ͬ��
	 */
	@Override
	public Throwable fillInStackTrace() {		
		return this;
	}

	
	
}
