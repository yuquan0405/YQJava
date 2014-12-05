package com.framework.exception;

/**
 * �Զ������ݿ�����쳣��
 * @author 
 *
 */
public class DBException extends Exception {
	private String  message;
	
	
	public DBException(String message) {
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
