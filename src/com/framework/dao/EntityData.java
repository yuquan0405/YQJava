package com.framework.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.framework.annotation.Column;
import com.framework.annotation.GenerationType;
import com.framework.annotation.Id;
import com.framework.annotation.Table;
import com.framework.dao.entity.EntityBean;
import com.framework.dao.entity.FieldBean;

/**
 * 实体反射数据存储
 * @author 
 *
 */
public class EntityData {
	
	private static Map<String,EntityBean> data = new Hashtable<String, EntityBean>();
	
	public static EntityBean get(Class cls){
		EntityBean bean = data.get(cls.getName());
		if(bean == null){
			System.out.println("load...");
			bean = new EntityBean();
			String _tableName ="";
			Table tb = (Table)cls.getAnnotation(Table.class);			
			if(tb != null && !"".equals(tb.name()) ){
				_tableName = tb.name();
			}else{
				_tableName = cls.getSimpleName();//默认取实体名称，注解有数据则取注解数据
			}
			bean.setTableName(_tableName);
			bean.setClassName(cls.getName());
			
			Field [] fields = cls.getDeclaredFields();
			String pk = "";
			List<FieldBean> primaryKey = new ArrayList<FieldBean>(); 
			List<FieldBean> lstFields = new ArrayList<FieldBean>();
			for(int i = 0; i < fields.length; i++){
				Field _field = fields[i];
				FieldBean _fieldBean = new FieldBean();
				Column column = _field.getAnnotation(Column.class);
				_fieldBean.setFieldName(_field.getName());
				
				//只显示加注释的列
				if(column != null && !"".equals(column.name()) ){
					_fieldBean.setColumnName( column.name());
					_fieldBean.setCommentName(column.comment());
				}else if(column != null){
					_fieldBean.setColumnName(_field.getName());
					_fieldBean.setCommentName(_fieldBean.getColumnName());
				}else{
					continue;
				}
				//按不加任何注释也显示列
//				if(column != null && !"".equals(column.name()) ){
//					_fieldBean.setColumnName( column.name());
//					_fieldBean.setCommentName(column.comment());
//				}else {
//					_fieldBean.setColumnName(_field.getName());
//					_fieldBean.setCommentName(_fieldBean.getColumnName());
//				}
					
				Id id = _field.getAnnotation(Id.class);
				if(id != null){
					
					_fieldBean.setPrimaryKey(true);
					_fieldBean.setType(id.type());
					_fieldBean.setSequence(id.sequence());
					primaryKey.add(_fieldBean);
				}else{
					_fieldBean.setPrimaryKey(false);
				}
				lstFields.add(_fieldBean);			
			}			
			bean.setPrimaryKey(primaryKey);
			bean.setFields(lstFields);
			EntityData.put(cls.getName(), bean);			
			
		}		
		return bean;
	}
	
	public static  void put(String key,EntityBean bean){
		data.put(key, bean);
	}
	

}
