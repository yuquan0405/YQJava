package com.framework.tag;

import java.util.*;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class FieldTag extends BodyTagSupport{
	
	/**
	 * 字段名称
	 */
	private String field;
	
	/**
	 * 显示名称
	 */
	private String name;
	
	/**
	 * 字段类型text/date/datetime/zd
	 */
	private String type;
	
	/**
	 * 宽度
	 */
	private String width;
	
	/**
	 * 字符\数字\日期格式化
	 */
	private String formater;
	
	/**
	 * 字典表名
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
