package com.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 主键
 * @author 
 *
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.FIELD})
public @interface Id {
	
	/**
	 * ID生成类型
	 * @return
	 */
	public GenerationType type() default GenerationType.DEFALUT;
	
	/**
	 * type＝GenerationType.SEQUENCE时需要序列名称
	 * @return
	 */
	public String sequence() default "";
	
	
}
