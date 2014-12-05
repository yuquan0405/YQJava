package com.framework.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class ArrayRowMapper implements RowMapper {
	
	public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
		ResultSetMetaData md = arg0.getMetaData();
		int num = md.getColumnCount();
		Object[] obj = new Object[num];
		for (int i = 1; i <= num; i++) {
			String colName = md.getColumnName(i).toLowerCase();
			Object value = arg0.getObject(i);
			if (colName != null && !colName.equals("") && value != null
					&& !value.equals("")) {
				if (colName.equals("rownum_")) {
					continue;
				}

				obj[i - 1] = arg0.getObject(i);

			}
		}
		return obj;
	}

}
