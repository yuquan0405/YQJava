package com.framework.exception;

/**
 * 自定义数据库操作异常类
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
	 * 重写该方法取消同步
	 */
	@Override
	public Throwable fillInStackTrace() {		
		return this;
	}

	
	
}
