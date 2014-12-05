package com.framework;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;
 

public class Context {
	
	/**
     * Spring�����Ķ��� 
    */
    private static ApplicationContext context = null;
    
    /**
     * ͨ��HttpServletRequest���ApplicationContext������ 
     */
    public static ApplicationContext getInstance(HttpServletRequest request) {
		if (context == null) {
			context = WebApplicationContextUtils.getRequiredWebApplicationContext(
					request.getSession().getServletContext());
		}
		return context;
	}
    
    /**
     * ͨ��ServletContext���ApplicationContext������ 
     */
    public static ApplicationContext getInstance(ServletContext servletContext) {
		if (context == null) {
			context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		}
		return context;
	}
    
    /**
     * ͨ��HttpServletRequest���JdbcTemplate����Bean
     */
    public static JdbcTemplate getJdbcTemplate(HttpServletRequest request) {
		return (JdbcTemplate) Context.getInstance(request).getBean(
				"jdbcTemplate");
	}
    
    /**
     * ͨ��ServletContext���JdbcTemplate����Bean
     */
    public static JdbcTemplate getJdbcTemplate(ServletContext servletContext) {
		return (JdbcTemplate) Context.getInstance(servletContext).getBean(
				"jdbcTemplate");
	}
    
    /**
     * ͨ��HttpServletRequest���beanName
     */
    public static Object getBean(HttpServletRequest request,String beanName) {
		return   Context.getInstance(request).getBean(beanName);
	}
    
    /**
     * ͨ��ServletContext���beanName
     */
    public static Object getBean(ServletContext servletContext,String beanName) {
		return Context.getInstance(servletContext).getBean(beanName);
	}
    
    /**
     * ͨ��HttpServletRequest���JdbcTemplate����Bean
     */
    public static JdbcTemplate getJdbcTemplateSqlServer(HttpServletRequest request) {
		return (JdbcTemplate) Context.getInstance(request).getBean(
				"jdbcTemplateSqlServer");
	}
}
