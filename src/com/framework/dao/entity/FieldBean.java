package com.framework.dao.entity;

import com.framework.annotation.GenerationType;

public class FieldBean {
	
	private String fieldName = "";
	private String columnName = "";
	private String commentName = "";
	private int length;
	private GenerationType type ;
	private boolean isPrimaryKey = false;
	private String sequence;
	
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public GenerationType getType() {
		return type;
	}
	public void setType(GenerationType type) {
		this.type = type;
	}
	public String getCommentName() {
		return commentName;
	}
	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}
	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	 
}
