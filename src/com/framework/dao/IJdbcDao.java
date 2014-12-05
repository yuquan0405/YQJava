package com.framework.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;

import com.framework.exception.DBException;

public interface IJdbcDao {

	public abstract <T> List<T> queryToObject(Class<T> cls, String sql,
			Object... param);

	public abstract <T> List<T> queryToObject(Class<T> cls, String sql);

	public abstract <T> T getToObject(Class<T> cls, String sql, Object... param);

	public abstract <T> T getToObject(Class<T> cls, String sql);

	/**
	 * 根据SQL查询返回数组的List集合
	 * 
	 * @param sqlStr
	 * @param param
	 * @return
	 */
	public abstract List<Object[]> queryToArray(String sqlStr, Object... param);

	/**
	 * 根据SQL查询返回数组的List集合
	 * 
	 * @param sqlStr
	 * @return
	 */
	public abstract List<Object[]> queryToArray(String sqlStr);

	public abstract <T> List<T> queryToMap(String sqlStr, Object... param);

	/**
	 * 根据SQL查询返回Map的List集合
	 * 
	 * @param sqlStr
	 * @return
	 */
	public abstract <T> List<T> queryToMap(String sqlStr);

	/**
	 * 根据SQL查询返回Map对象
	 * 
	 * @param sqlStr
	 * @return
	 */
	public abstract Map getToMap(String sqlStr, Object... param);

	/**
	 * 根据SQL查询返回Map对象
	 * 
	 * @param sqlStr
	 * @return
	 */
	public abstract Map getToMap(String sqlStr);

	/**
	 * 根据SQL查询返回数组对象
	 * 
	 * @param sqlStr
	 * @return
	 */
	public abstract Object[] getToArray(String sqlStr, Object... param);

	/**
	 * 根据SQL查询返回数组对象
	 * 
	 * @param sqlStr
	 * @return
	 */
	public abstract Object[] getToArray(String sqlStr);

	/**
	 * 批量执行 格式相同的sql
	 * @param sql
	 * @param s
	 * @return
	 */
	public abstract int[] batch(String sql, final String[] s);

	/**
	 * 批量执行格式不同的sql
	 * @param sqls
	 * @return
	 */
	public abstract int[] batch(String[] sqls);

	/**
	 * 批量执行SQL<br>
	 * jdbc.batchExec(sql, 2, new Object[][]{{"a","b"},{"1","2"}});<br>
	 * a,b为第????
	 * @param sql
	 * @param size
	 * @param args
	 * @return
	 */
	public abstract int[] batchExec(String sql, final int size,
			final Object[]... args);

	/**
	 * 得到序列
	 * 
	 * @param seq
	 * @return
	 */
	public abstract int getSequence(String seq);

	/**
	 * 执行SQL
	 * @param sql
	 * @param list
	 * @return
	 */
	public abstract int execSqlUpdate(String sql, Object[] objs);

	/**
	 * 执行SQL
	 * @param sql
	 * @return
	 */
	public abstract int execSqlUpdate(String sql);

	/**
	 * 执行存储过程
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	public abstract int execProcedure(String sql, Object[] args)
			throws SQLException;

	/**
	 * 得到当前事务状??
	 * 
	 * @return
	 */
	public abstract TransactionStatus getTransactionStatus() throws DBException;

	/**
	 * 手提交事??
	 * 
	 * @param status
	 */
	public abstract void commitTransaction(TransactionStatus status)
			throws DBException;

	/**
	 * 手动回滚事务
	 * 
	 * @param status
	 */
	public abstract void rollbackTransaction(TransactionStatus status)
			throws DBException;

	/**
	 * 根据当前系统的数据库类型，组合分页SQL
	 * @param sql
	 * @param pageIndex 当前页数（从1??????
	 * @param pageSize 每页大小
	 * @return
	 */
	public abstract String pageing(String sql, int pageIndex, int pageSize);

	/**
	 * 得到总记录数
	 * @param sql
	 * @param param
	 * @return
	 */
	public abstract int getRowSize(String sql, Object... param);

	/**
	 * 得到总记录数
	 * @param sql
	 * @return
	 */
	public abstract int getRowSize(String sql);

	/**
	 * 得到事务
	 * @return
	 */
	public abstract DataSourceTransactionManager getTransaction();

	public abstract void setTransaction(DataSourceTransactionManager transaction);

	public abstract String getJdbcType();

	public abstract void setJdbcType(String jdbcType);

	/**
	 * 删除对象
	 * @param obj
	 * @return
	 */
	public abstract int delete(Object obj);

	/**
	 * 根据主健加载对象数据
	 * @param obj
	 * @return
	 */
	public abstract <T> T load(T obj);

	/**
	 * 保存对象
	 * @param obj
	 * @return
	 */
	public abstract int save(Object obj);

	/**
	 * 更新对象
	 * @param obj
	 * @param notUpdateNull 不更新为空的
	 * @return
	 */
	public abstract int update(Object obj, boolean notUpdateNull);

	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract String getUUID();

}