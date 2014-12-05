package com.framework.tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class PermissionTag extends BodyTagSupport{
	
	private String code;

	@Override
	public int doAfterBody() throws JspException {
		// TODO Auto-generated method stub
		return super.doAfterBody();
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		HttpSession session = pageContext.getSession();
		if(!"1".equals(code)){
			return this.SKIP_BODY;
		} 
	//	JspWriter w = pageContext.getOut();
		try { 
			bodyContent.writeOut(this.bodyContent.getEnclosingWriter());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}

	@Override
	public void doInitBody() throws JspException {
		// TODO Auto-generated method stub
		super.doInitBody();
	}

	@Override
	public int doStartTag() throws JspException { 
 
		return super.doStartTag();
	}

	@Override
	public BodyContent getBodyContent() {
		 
		return super.getBodyContent();
	}

	@Override
	public JspWriter getPreviousOut() {
		// TODO Auto-generated method stub
		return super.getPreviousOut();
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		super.release();
	}

	@Override
	public void setBodyContent(BodyContent b) {
		// TODO Auto-generated method stub
		super.setBodyContent(b);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	

}
