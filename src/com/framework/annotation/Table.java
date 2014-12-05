package com.framework.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ΚµΜε±ν
 * @author 
 *
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.TYPE})
public @interface Table {	
	public String name() default "";
}
