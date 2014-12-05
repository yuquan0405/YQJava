package com.framework;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;
 

public class Context {
	
	/**
     * Spring上下文对象 
    */
    private static ApplicationContext context = null;
    
    /**
     * 通过HttpServletRequest获得ApplicationContext上下文 
     */
    public static ApplicationContext getInstance(HttpServletRequest request) {
		if (context == null) {
			context = WebApplicationContextUtils.getRequiredWebApplicationContext(
					request.getSession().getServletContext());
		}
		return context;
	}
    
    /**
     * 通过ServletContext获得ApplicationContext上下文 
     */
    public static ApplicationContext getInstance(ServletContext servletContext) {
		if (context == null) {
			context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		}
		return context;
	}
    
    /**
     * 通过HttpServletRequest获得JdbcTemplate对象Bean
     */
    public static JdbcTemplate getJdbcTemplate(HttpServletRequest request) {
		return (JdbcTemplate) Context.getInstance(request).getBean(
				"jdbcTemplate");
	}
    
    /**
     * 通过ServletContext获得JdbcTemplate对象Bean
     */
    public static JdbcTemplate getJdbcTemplate(ServletContext servletContext) {
		return (JdbcTemplate) Context.getInstance(servletContext).getBean(
				"jdbcTemplate");
	}
    
    /**
     * 通过HttpServletRequest获得beanName
     */
    public static Object getBean(HttpServletRequest request,String beanName) {
		return   Context.getInstance(request).getBean(beanName);
	}
    
    /**
     * 通过ServletContext获得beanName
     */
    public static Object getBean(ServletContext servletContext,String beanName) {
		return Context.getInstance(servletContext).getBean(beanName);
	}
    
    /**
     * 通过HttpServletRequest获得JdbcTemplate对象Bean
     */
    public static JdbcTemplate getJdbcTemplateSqlServer(HttpServletRequest request) {
		return (JdbcTemplate) Context.getInstance(request).getBean(
				"jdbcTemplateSqlServer");
	}
}
