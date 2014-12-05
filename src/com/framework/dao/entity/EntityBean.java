package com.framework.dao.entity;

import java.util.List;

public class EntityBean {
	
	/**
	 * ����
	 */
	private String tableName;
	/**
	 * ʵ����
	 */
	private String className;
	/**
	 * ����
	 */
	private List<FieldBean> primaryKey;
	
	/**
	 * �ֶ�
	 */
	private List<FieldBean> fields;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}	

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<FieldBean> getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(List<FieldBean> primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<FieldBean> getFields() {
		return fields;
	}

	public void setFields(List<FieldBean> fields) {
		this.fields = fields;
	}
}
