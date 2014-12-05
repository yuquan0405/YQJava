package com.framework.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import com.framework.dao.entity.EntityBean;
import com.framework.dao.entity.FieldBean;
import com.framework.util.ClassUtil;

/**
 * 实体DAO操作方法支持注解模式
 * @author Administrator
 *
 */
public class BeanRowMapper implements RowMapper {
	private final Log logger = LogFactory.getLog(this.getClass());
	private Class clazz;
	public BeanRowMapper(Class clazz) {
		this.clazz = clazz;
	}
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			Object obj = clazz.newInstance();
			ResultSetMetaData meta = rs.getMetaData();
			int iCount = meta.getColumnCount();
			EntityBean bean = EntityData.get(obj.getClass());
			List<FieldBean> fields = bean.getFields();
			
			for (int i = 1; i <= iCount; i++) {
				String colName = meta.getColumnName(i).toLowerCase();			
				Object value = rs.getObject(i);
				if (value != null && !colName.equals("rownum_")){
					boolean _flag = false;
					String fieldName = colName;
					for(int j = 0; j < fields.size(); j++){
						FieldBean _field = fields.get(j);
						if(colName != null && colName.equals(_field.getColumnName().toLowerCase())){
							_flag = true;							
							fieldName = _field.getFieldName();
							break;
						}
					}
					if(_flag){
						ClassUtil.setFieldValue(obj, fieldName, value);
					}					
				}				
			}
			return obj;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}


}
