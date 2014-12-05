package com.framework.tag;

import java.util.*;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class FieldTag extends BodyTagSupport{
	
	/**
	 * �ֶ�����
	 */
	private String field;
	
	/**
	 * ��ʾ����
	 */
	private String name;
	
	/**
	 * �ֶ�����text/date/datetime/zd
	 */
	private String type;
	
	/**
	 * ���
	 */
	private String width;
	
	/**
	 * �ַ�\����\���ڸ�ʽ��
	 */
	private String formater;
	
	/**
	 * �ֵ����
	 */
	private String tableName;	
	

	
	@Override
	public int doStartTag() throws JspException {

		GridViewTag gridViewTag = (GridViewTag)this.findAncestorWithClass(this, GridViewTag.class);
		if(gridViewTag != null) {  
			List<FieldTag> fieldTags = gridViewTag.getFieldTags();
			FieldTag _tag = new FieldTag();
			_tag.setField(this.field);
			_tag.setName(this.name);
			_tag.setTableName(this.tableName);
			_tag.setFormater(this.formater);
			_tag.setWidth(this.width);
		
			fieldTags.add(_tag);
			gridViewTag.setFieldTags(fieldTags);
		}		
		return super.doStartTag();
		
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getFormater() {
		return formater;
	}

	public void setFormater(String formater) {
		this.formater = formater;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
	

}
