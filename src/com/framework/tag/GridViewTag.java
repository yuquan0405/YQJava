package com.framework.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.framework.util.StringUtil;

public class GridViewTag extends BodyTagSupport{
	
	private String needLineNum;
	
	private String needCheckBox;
	
	private String beanName;
	
	private String isSql;
	
	private List<FieldTag> fieldTags = new ArrayList<FieldTag>();
	
	public void clear(){
		this.needLineNum = null;
		this.needCheckBox = null;
		this.beanName = null;
		this.isSql = null;
		this.fieldTags = new ArrayList<FieldTag>();
	}
	@Override
	public int doAfterBody() throws JspException {
		// TODO Auto-generated method stub
		return super.doAfterBody();
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
//		HttpSession session = pageContext.getSession();

		JspWriter w = pageContext.getOut();
		StringBuffer html = new StringBuffer();
		html.append("<table style='border:1px  solid;'><tr>");
		html.append("<th width='20px'>ÐòºÅ</th>");
		for(int i = 0; i < this.getFieldTags().size(); i++){
			FieldTag _tag = this.getFieldTags().get(i);
			
			html.append("<th");
			if(!StringUtil.isEmpty(_tag.getWidth())){
				html.append(" width='").append(_tag.getWidth()).append("px' ");
			}
			html.append(">");
			html.append(this.getFieldTags().get(i).getName());
			html.append("</th>");
			
		}
		html.append("</tr>");
		for(int j = 0; j < 20; j++){
			html.append("<tr>");
			html.append("<td>"+(j+1)+"</td>");
			for(int i = 0; i < this.getFieldTags().size(); i++){
				FieldTag _tag = this.getFieldTags().get(i);
				html.append("<td>");
				html.append(this.getFieldTags().get(i).getName());
				html.append("</td>");
			}
			html.append("</tr>");
		}
		
		html.append("</table>");
		
		try {
			w.write(html.toString());
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
		clear();
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

	public String getNeedLineNum() {
		return needLineNum;
	}

	public void setNeedLineNum(String needLineNum) {
		this.needLineNum = needLineNum;
	}

	public String getNeedCheckBox() {
		return needCheckBox;
	}

	public void setNeedCheckBox(String needCheckBox) {
		this.needCheckBox = needCheckBox;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getIsSql() {
		return isSql;
	}

	public void setIsSql(String isSql) {
		this.isSql = isSql;
	}

	public List<FieldTag> getFieldTags() {
		return fieldTags;
	}

	public void setFieldTags(List<FieldTag> fieldTags) {
		this.fieldTags = fieldTags;
	}
	
	
}
