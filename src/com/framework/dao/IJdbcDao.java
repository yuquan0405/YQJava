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
	 * ����SQL��ѯ���������List����
	 * 
	 * @param sqlStr
	 * @param param
	 * @return
	 */
	public abstract List<Object[]> queryToArray(String sqlStr, Object... param);

	/**
	 * ����SQL��ѯ���������List����
	 * 
	 * @param sqlStr
	 * @return
	 */
	public abstract List<Object[]> queryToArray(String sqlStr);

	public abstract <T> List<T> queryToMap(String sqlStr, Object... param);

	/**
	 * ����SQL��ѯ����Map��List����
	 * 
	 * @param sqlStr
	 * @return
	 */
	public abstract <T> List<T> queryToMap(String sqlStr);

	/**
	 * ����SQL��ѯ����Map����
	 * 
	 * @param sqlStr
	 * @return
	 */
	public abstract Map getToMap(String sqlStr, Object... param);

	/**
	 * ����SQL��ѯ����Map����
	 * 
	 * @param sqlStr
	 * @return
	 */
	public abstract Map getToMap(String sqlStr);

	/**
	 * ����SQL��ѯ�����������
	 * 
	 * @param sqlStr
	 * @return
	 */
	public abstract Object[] getToArray(String sqlStr, Object... param);

	/**
	 * ����SQL��ѯ�����������
	 * 
	 * @param sqlStr
	 * @return
	 */
	public abstract Object[] getToArray(String sqlStr);

	/**
	 * ����ִ�� ��ʽ��ͬ��sql
	 * @param sql
	 * @param s
	 * @return
	 */
	public abstract int[] batch(String sql, final String[] s);

	/**
	 * ����ִ�и�ʽ��ͬ��sql
	 * @param sqls
	 * @return
	 */
	public abstract int[] batch(String[] sqls);

	/**
	 * ����ִ��SQL<br>
	 * jdbc.batchExec(sql, 2, new Object[][]{{"a","b"},{"1","2"}});<br>
	 * a,bΪ��????
	 * @param sql
	 * @param size
	 * @param args
	 * @return
	 */
	public abstract int[] batchExec(String sql, final int size,
			final Object[]... args);

	/**
	 * �õ�����
	 * 
	 * @param seq
	 * @return
	 */
	public abstract int getSequence(String seq);

	/**
	 * ִ��SQL
	 * @param sql
	 * @param list
	 * @return
	 */
	public abstract int execSqlUpdate(String sql, Object[] objs);

	/**
	 * ִ��SQL
	 * @param sql
	 * @return
	 */
	public abstract int execSqlUpdate(String sql);

	/**
	 * ִ�д洢����
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	public abstract int execProcedure(String sql, Object[] args)
			throws SQLException;

	/**
	 * �õ���ǰ����״??
	 * 
	 * @return
	 */
	public abstract TransactionStatus getTransactionStatus() throws DBException;

	/**
	 * ���ύ��??
	 * 
	 * @param status
	 */
	public abstract void commitTransaction(TransactionStatus status)
			throws DBException;

	/**
	 * �ֶ��ع�����
	 * 
	 * @param status
	 */
	public abstract void rollbackTransaction(TransactionStatus status)
			throws DBException;

	/**
	 * ���ݵ�ǰϵͳ�����ݿ����ͣ���Ϸ�ҳSQL
	 * @param sql
	 * @param pageIndex ��ǰҳ������1??????
	 * @param pageSize ÿҳ��С
	 * @return
	 */
	public abstract String pageing(String sql, int pageIndex, int pageSize);

	/**
	 * �õ��ܼ�¼��
	 * @param sql
	 * @param param
	 * @return
	 */
	public abstract int getRowSize(String sql, Object... param);

	/**
	 * �õ��ܼ�¼��
	 * @param sql
	 * @return
	 */
	public abstract int getRowSize(String sql);

	/**
	 * �õ�����
	 * @return
	 */
	public abstract DataSourceTransactionManager getTransaction();

	public abstract void setTransaction(DataSourceTransactionManager transaction);

	public abstract String getJdbcType();

	public abstract void setJdbcType(String jdbcType);

	/**
	 * ɾ������
	 * @param obj
	 * @return
	 */
	public abstract int delete(Object obj);

	/**
	 * �����������ض�������
	 * @param obj
	 * @return
	 */
	public abstract <T> T load(T obj);

	/**
	 * �������
	 * @param obj
	 * @return
	 */
	public abstract int save(Object obj);

	/**
	 * ���¶���
	 * @param obj
	 * @param notUpdateNull ������Ϊ�յ�
	 * @return
	 */
	public abstract int update(Object obj, boolean notUpdateNull);

	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract String getUUID();

}