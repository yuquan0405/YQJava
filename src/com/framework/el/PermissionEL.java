package com.framework.el;

import javax.servlet.jsp.JspException;

public class PermissionEL {
	
	
	public static boolean hasPermission(String code) {
		if("1".equals(code)){
			
			return true;
		}else{
			
			return false;
		}
	}

}
