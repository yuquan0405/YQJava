package com.framework.annotation;

public enum GenerationType {	
	/**
	 * 默认,自定义填充主健
	 */
	DEFALUT,
	/**
	 * 使用UUID生成主键
	 */
	UUID,
	/**
	 * 使用MAX+1生成主键MAX,暂不支持
	 */
	
	/**
	 * ORACLE数据库时使用SEQUENCE生成主健 
	 */
	SEQUENCE
}
