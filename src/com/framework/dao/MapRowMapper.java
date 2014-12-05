package com.framework.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException; 
import java.util.HashMap; 
import java.util.Map;
 
import org.springframework.jdbc.core.RowMapper;

/**
 * Map
 *
 */
public class MapRowMapper implements RowMapper {

	@SuppressWarnings("unchecked")
	public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
		Map mapOfColValues = new HashMap();
		ResultSetMetaData meta = arg0.getMetaData();
		int iCount = meta.getColumnCount();
		for (int i = 1; i <= iCount; i++) {
			String colName = meta.getColumnName(i).toLowerCase();
			Object value = arg0.getObject(i);
			if (colName != null && !colName.equals(""))
				mapOfColValues.put(colName, value);
		}
		return mapOfColValues;
	}

}
