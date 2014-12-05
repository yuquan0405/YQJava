package com.framework.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.ClassUtils;

import com.framework.util.ClassUtil;

public class ClassRowMapper implements RowMapper {
	private final Log logger = LogFactory.getLog(this.getClass());
	private Class clazz;
	public ClassRowMapper(Class clazz) {
		this.clazz = clazz;
	}
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			Object obj = clazz.newInstance();
			ResultSetMetaData meta = rs.getMetaData();
			int iCount = meta.getColumnCount();
			List<String> fields = ClassUtil.getFields(obj);
			for (int i = 1; i <= iCount; i++) {
				String colName = meta.getColumnName(i).toLowerCase();			
				Object value = rs.getObject(i);
				if (value != null && !colName.equals("rownum_")){
					boolean _flag = false;
					String fieldName = colName;
					for(int j = 0; j < fields.size(); j++){
						if(colName != null && colName.equals(fields.get(j).toLowerCase())){
							_flag = true;
							fieldName = fields.get(j);
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
