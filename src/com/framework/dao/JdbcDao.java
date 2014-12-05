package com.framework.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.framework.annotation.GenerationType;
import com.framework.dao.entity.EntityBean;
import com.framework.dao.entity.FieldBean;
import com.framework.exception.DBException;
import com.framework.util.ClassUtil;
 
 

/**
 * JDBC数据库操作类<br>
 * query 命名开始的为结果集查询<br>
 * get 命名开始的为单对象查询
 * @author Administrator
 * 
 */
public class JdbcDao {
	/**
	 * 日志类
	 */
	private static Logger logger = Logger.getLogger(JdbcDao.class);
	
	/**
	 * spring jdbcTemplate bean 
	 * 
	 */
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * jdbc type is oracle/mysql/sqlserver
	 */
	private String jdbcType = "oracle";
	
	/**
	 * transaction status 
	 */
	private DataSourceTransactionManager transaction; 
	
	/**
	 * 根据SQL查询实体对象集合（可带参数）
	 * @param cls 为实体类
	 * @param sql 为SQL语句
	 * @param param 为参数数组
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryToObject(Class<T> cls,String sql, Object... param) {
		logger.debug(sql);		
		List<T> list = null;//this.jdbcTemplate.query(sql, param, new ClassRowMapper(cls))<T>
		list = this.jdbcTemplate.query(sql, param, new ClassRowMapper(cls));
		if(list.isEmpty()) {
			return null;
		}
		return list;
	}
	
	/**
	 * 根据SQL查询实体对象集合
	 * @param cls 为实体类
	 * @param sql 为SQL语句
	 */
	public <T> List<T> queryToObject(Class<T> cls,String sql){
		return queryToObject(cls, sql, null);
	}
	
	/**
	 * 根据SQL得到实体对象（可带参数）
	 * @param cls 为实体类
	 * @param sql 为SQL语句
	 * @param param 为参数数组
	 */
	public <T> T getToObject(Class<T> cls,String sql,Object ...param){
		List<T> list = queryToObject(cls,sql,param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 根据SQL得到实体对象
	 * @param cls 为实体类
	 * @param sql 为SQL语句
	 */
	public <T> T getToObject(Class<T> cls,String sql){
		return getToObject(cls,sql,null);
	}

	/**
	 * 根据SQL查询数组对象集合（可带参数）
	 * @param sqlStr 为SQL语句
	 * @param param 为参数数组
	 */
	public <T> List<T> queryToArray(String sqlStr, Object... param) {
		logger.debug(sqlStr);
		List<T> list = null;
		if (param == null) {
			list = this.jdbcTemplate.query(sqlStr, new ArrayRowMapper());
		} else {
			list = this.jdbcTemplate.query(sqlStr, param, new ArrayRowMapper());
		}
		return list;
	}
	
	
	/**
	 * 根据SQL查询数组对象集合
	 * @param sqlStr 为SQL语句
	 */
	public <T> List<T> queryToArray(String sqlStr) {
		return queryToArray(sqlStr, null);
	}
	
	/**
	 * 根据SQL得到数组对象（可带参数）
	 * @param sqlStr 为SQL语句
	 * @param param 为参数数组
	 */
	public Object[] getToArray(String sqlStr, Object... param) {
		List<Object[]> list = this.queryToArray(sqlStr, param);
		if (list.size() <= 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * 根据SQL得到数组对象
	 * @param sqlStr 为SQL语句
	 */
	public Object[] getToArray(String sqlStr) {
		return getToArray(sqlStr, null);
	}
	
	/**
	 * 根据SQL查询map对象集合（可带参数）
	 * @param sqlStr 为SQL语句
	 * @param param 为参数数组
	 */
	public <T> List<T> queryToMap(String sqlStr, Object... param) {
		logger.debug(sqlStr);
		List<T> list = null;
		if (param == null) {
			list = this.jdbcTemplate.query(sqlStr, new MapRowMapper());
		} else {
			list = this.jdbcTemplate.query(sqlStr, param, new MapRowMapper());
		}
		return list;
	}
	
	/**
	 * 根据SQL查询map对象集合
	 * @param sqlStr 为SQL语句
	 */
	public <T> List<T> queryToMap(String sqlStr) {
		return queryToMap(sqlStr, null);
	}

	/**
	 * 根据SQL得到map对象（可带参数）
	 * @param sqlStr 为SQL语句
	 * @param param 为参数数组
	 */
	public Map getToMap(String sqlStr, Object...param) {
		List<Map> list = this.queryToMap(sqlStr, param);
		if (list.size() <= 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * 根据SQL得到map对象
	 * @param sqlStr 为SQL语句
	 */
	public Map getToMap(String sqlStr) {
		return getToMap(sqlStr, null);
	}

	/**
	 * 批量执行SQL
	 * @param sql 为SQL语句 如 insert into demo(column1,column2)values(?,?)
	 * @param s 如 new Object[][]{{"a","b"},{"1","2"}})
	 * @return 
	 */
	public int[] batch(String sql, final String[] s) {
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
	           public void setValues(PreparedStatement ps, int i) throws SQLException { 
	              String dto = s[i];
	              ps.setString(1, dto);
	           }
	           public int getBatchSize() {
	              return s.length;
	           }

	       };
	       logger.debug(sql);
	       int [] i = this.jdbcTemplate.batchUpdate(sql, setter);
	       return i;
	}
	
	/**
	 * 批量执行SQL(固定参数长度)
	 * @param sql 为SQL语句 如 insert into demo(column1,column2)values(?,?)
	 * @param size 为参数长度
	 * @param args 如 new Object[][]{{"a","b"},{"1","2"}})
	 * @return
	 */
	public int[] batchExec(String sql, final int size, final Object[]... args) {
		logger.debug(sql);
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				for (int j = 0; j < args.length; j++) {
					Object[] obj = args[j];
					ps.setObject(j + 1, obj[i]);
				} 
			} 
			public int getBatchSize() {
				return size;
			}
		};
		int[] retval = this.jdbcTemplate.batchUpdate(sql, setter);
		return retval;
	}

	/**
	 * 得到序列（oracle特有方法）
	 * @param seq 序列名称
	 * @return
	 */
	public long getSequence(String seq) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ").append(seq.trim()).append(".NEXTVAL")
				.append(" from dual");
		logger.debug(sb.toString());
		long sequ = this.jdbcTemplate.queryForLong(sb.toString());
		return sequ;
	}
	
	/**
	 * 执行SQL方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public int execSqlUpdate(String sql, Object... params){ 
		int i = this.jdbcTemplate.update(sql, params);
		logger.debug(sql);
		return i;
	}
	
	/**
	 * 执行SQL方法
	 * @param sql
	 * @return
	 */
	public int execSqlUpdate(String sql){ 
		int i = execSqlUpdate(sql,null);
		logger.debug(sql);
		return i;
	}

	/**
	 * 执行存储过程
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public int execProcedure(String sql, Object... args) throws SQLException {
		logger.debug(sql);
		Connection con = this.jdbcTemplate.getDataSource().getConnection();
		CallableStatement proc = con.prepareCall("{"+sql+"}");
		for(int i =1 ;args!=null && i <= args.length; i++){
			proc.setObject(i, args[i]);
		}
		logger.debug(sql); 
		return proc.execute()?1:0;
	}

	/**
	 * 得到事务状态方法
	 * @return
	 * @throws DBException
	 */
	public TransactionStatus getTransactionStatus() throws DBException{
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transaction.getTransaction(definition);
		return status;
	}

	/**
	 * 提交事务
	 * @param status
	 * @throws DBException
	 */
	public void commitTransaction(TransactionStatus status) throws DBException{
		transaction.commit(status);
	}

	/**
	 * 回滚事务
	 * @param status
	 * @throws DBException
	 */
	public void rollbackTransaction(TransactionStatus status)throws DBException {
		transaction.rollback(status);
	}
	
	/**
	 * 计算分页总页
	 * @param count
	 * @param pageSize
	 * @return
	 */
	public static int getPageSize(int count,int pageSize){
		int total = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		return total;
	}

	/**
	 * 得到分页SQL
	 * @param sql
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public String pageing(String sql, int pageIndex, int pageSize) {
		pageIndex = (pageIndex - 1) * pageSize;
		StringBuffer sbstr = new StringBuffer("");
		if ("oracle".equals(this.getJdbcType())) {
			pageSize += pageIndex;
			sbstr.append("select * from ( select row_.*, rownum rownum_ from ( ");
			sbstr.append(sql);
			sbstr.append(" ) row_ where rownum <= " + pageSize
					+ ") where rownum_ > " + pageIndex);
		} else if ("mysql".equals(this.getJdbcType())) {
			sbstr.append(sql);
			sbstr.append(" limit " + pageIndex + "," + pageSize);
		} else {
			sbstr.append(sql);
		}
		return sbstr.toString();
	}
	
	/**
	 * 得到SQL的总记录数
	 * @param sql
	 * @param param
	 * @return
	 */
	public long getRowSize(String sql, Object...param) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) from ");
		sb.append("(");
		sb.append(sql);
		sb.append(")"); 
		return this.jdbcTemplate.queryForLong(sb.toString(), param);
	}
	
	/**
	 * 得到SQL的总记录数
	 * @param sql
	 * @return
	 */
	public long getRowSize(String sql){
		return getRowSize(sql,null);
	} 
	
	/**
	 * 得到事务对象
	 * @return
	 */
	public DataSourceTransactionManager getTransaction() {
		return transaction;
	}

	/**
	 * 设置事务对象
	 * @param transaction
	 */
	public void setTransaction(DataSourceTransactionManager transaction) {
		this.transaction = transaction;
	}

	/**
	 * 得到数据库类型
	 * @return
	 */
	public String getJdbcType() {
		return jdbcType;
	}

	/**
	 * 设置数据库类型
	 * @param jdbcType
	 */
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	/**
	 * 删除实体对象（根据主健）
	 * @param obj
	 * @return
	 */
	public int delete(Object obj) { 		
		int rs = 0;
		EntityBean bean = EntityData.get(obj.getClass());
		if(bean == null || bean.getPrimaryKey() == null ){
			return rs;
		}
		List<FieldBean> primarys = bean.getPrimaryKey();
		String sql = "DELETE FROM ";
		sql += bean.getTableName();
		sql += " WHERE ";
		
		Object [] objs = new Object[primarys.size()];
		for(int i = 0; i < primarys.size(); i++){
			FieldBean fbean = primarys.get(i);
			if(i > 0){
				sql += " and ";
			}
			sql += fbean.getColumnName().toUpperCase() + "=? ";
			try {
				objs[i] = ClassUtil.getFieldValue(obj, fbean.getFieldName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		rs = this.execSqlUpdate(sql, objs);
		logger.debug(sql);
		return rs;
	}
	
	/**
	 * 加载实体对象（根据主健）
	 * @param obj
	 * @return
	 */
	public <T> T load(T obj) {		 
		EntityBean bean = EntityData.get(obj.getClass());
		if(bean == null || bean.getPrimaryKey() == null ){
			return null;
		}		
		StringBuffer sql = new StringBuffer("SELECT  ");
		List<FieldBean> lstF = bean.getFields();
		for(int i = 0; i < lstF.size(); i++){
			if( i > 0){				
				sql.append(",");
			}
			sql.append(lstF.get(i).getColumnName().toUpperCase());
		}
		sql.append(" FROM ");
		sql.append(bean.getTableName().toUpperCase());
		sql.append(" WHERE ");		
		List<FieldBean> primarys = bean.getPrimaryKey();
		Object [] objs = new Object[primarys.size()];
		for(int i = 0; i < primarys.size(); i++){
			FieldBean fbean = primarys.get(i);
			if(i > 0){
				sql.append(" AND ");
			}
			sql.append(primarys.get(i).getColumnName().toUpperCase());
			sql.append("=?");
			try {
				objs[i] = ClassUtil.getFieldValue(obj, fbean.getFieldName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		Object object = this.getToObject(obj.getClass(), sql.toString(),objs);
		logger.debug(sql);
		return (T)object;
	}
	
	/**
	 * 保存实体对象(根据主健)
	 * @param obj
	 * @return
	 */
	public int save(Object obj) { 		
		int rs = 0;
		EntityBean bean = EntityData.get(obj.getClass());
		if(bean == null || bean.getPrimaryKey() == null ){
			return rs;
		}
		StringBuffer sql = new StringBuffer("INSERT INTO ");
		sql.append(bean.getTableName().toUpperCase());
		sql.append("(");		
		List<FieldBean> lstF = bean.getFields();
		List<Object> param = new ArrayList<Object>();
		for(int i = 0; i < lstF.size(); i++){
			if( i > 0){				
				sql.append(",");
			}
			sql.append(lstF.get(i).getColumnName().toUpperCase());
		} 
		sql.append(") VALUES("); 
		for(int i = 0; i < lstF.size(); i++){
			FieldBean _field = lstF.get(i);
			if( i > 0){				
				sql.append(",");
			}
			
			if(_field.isPrimaryKey()){
				if(_field.getType() == null || _field.getType().equals(GenerationType.DEFALUT)){
					try {
						sql.append("?");
						param.add(ClassUtil.getFieldValue(obj, _field.getFieldName()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if (_field.getType() != null && _field.getType().equals(GenerationType.SEQUENCE)){			
					sql.append(_field.getSequence());
					sql.append(".nextval");
				}else if (_field.getType() != null && _field.getType().equals(GenerationType.UUID)){			
					sql.append(this.getUUID());
				}
			}else{
				try {
					sql.append("?");
					param.add(ClassUtil.getFieldValue(obj, _field.getFieldName()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		sql.append(")");
		rs = this.execSqlUpdate(sql.toString(), param.toArray());
		logger.debug(sql);
		return rs;
	}
	
	/***
	 * 更新实体对象(根据主健)
	 * @param obj
	 * @param updateNull 是否更新为空属性 true/false
	 * @return
	 */
	public int update(Object obj,boolean updateNull) { 		
		int rs = 0;
		EntityBean bean = EntityData.get(obj.getClass());
		if(bean == null || bean.getPrimaryKey() == null ){
			return rs;
		}
		StringBuffer sql = new StringBuffer("UPDATE ");
		sql.append(bean.getTableName().toUpperCase());
		List<FieldBean> lstF = bean.getFields();
		List<Object> param = new ArrayList<Object>();
		StringBuffer sqlu = new StringBuffer();
		for(int i = 0; i < lstF.size(); i++){
			FieldBean _field = lstF.get(i);
			if(!_field.isPrimaryKey()){				
				try {
					Object _value = ClassUtil.getFieldValue(obj, _field.getFieldName());
					if( updateNull ){
						if(_value != null){
							sqlu.append(",");
							sqlu.append(lstF.get(i).getColumnName().toUpperCase());
							sqlu.append("=?");
							param.add(_value);
						}
					} else{
						sqlu.append(",");
						sqlu.append(lstF.get(i).getColumnName().toUpperCase());
						sqlu.append("=?");
						param.add(_value);
					}				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 			
		} 
		sql.append(" SET ");
		sql.append(sqlu.substring(1));
		sql.append(" WHERE ");
		List<FieldBean> primarys = bean.getPrimaryKey();		
		for(int i = 0; i < primarys.size(); i++){
			FieldBean fbean = primarys.get(i);
			if(i > 0){
				sql.append(" AND ");
			}
			sql.append(primarys.get(i).getColumnName().toUpperCase());
			sql.append("=?");
			try {
				param.add(ClassUtil.getFieldValue(obj, fbean.getFieldName()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(sql);
		rs = this.execSqlUpdate(sql.toString(), param.toArray());
		logger.debug(sql);
		return rs;
	}

	/**
	 * 得到Spring jdbcTemplate
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * 设置Spring jdbcTemplate
	 * @param jdbcTemplate
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 得到uuid  
	 * 
	 * @return oracle为 sys_guid();mysql 为uuid()
	 */
	public String getUUID(){
		String uuid = "";
		if("oracle".equals(this.getJdbcType().toLowerCase())){
			uuid = "sys_guid()";
		}else if("mysql".equals(this.getJdbcType().toLowerCase())){
			uuid = "uuid()";
		}else{
			uuid = "''";
		}
		return uuid;
	}
	

}
