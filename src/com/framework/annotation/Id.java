package com.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ����
 * @author 
 *
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.FIELD})
public @interface Id {
	
	/**
	 * ID��������
	 * @return
	 */
	public GenerationType type() default GenerationType.DEFALUT;
	
	/**
	 * type��GenerationType.SEQUENCEʱ��Ҫ��������
	 * @return
	 */
	public String sequence() default "";
	
	
}
