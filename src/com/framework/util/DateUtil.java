package com.framework.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
	 * ��ȡǰһ������yyyyMMdd
	 * 
	 * @see �����ԣ��������02�·ݻ�����������ô�������Ч�����Դ�������
	 * @see calendar.set(Calendar.YEAR, 2013);
	 * @see calendar.set(Calendar.MONTH, 0);
	 * @see calendar.set(Calendar.DATE, 1);
	 * @see ����ʱ������ŵ�<code>calendar.add(Calendar.DATE, -1);</code>ǰ�漴��
	 * @return ���ص����ڸ�ʽΪyyyyMMdd
	 */
	public static String getYestoday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
	}

	/**
	 * ��ȡ��ǰ������yyyyMMdd
	 */
	public static String getCurrentDate() {
		return getCurrentDateTime("yyyyMMdd");
	}

	/**
	 * ��ȡ��ǰ��ʱ��yyyyMMddHHmmss
	 */
	public static String getCurrentTime() {
		return getCurrentDateTime("yyyyMMddHHmmss");
	}
	/**
	 * ��ȡ��ǰ��ʱ��yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDateTime() {
		return getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * ָ����ʽ��ǰ����
	 * @param fmt �� yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentDateTime(String fmt) {
		return new SimpleDateFormat(fmt).format(new Date());
	}
	
	/**
	 * ָ����ʽ����
	 * @param fmt �� yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatDateTime(Date date,String fmt) {
		return new SimpleDateFormat(fmt).format(date);
	}
	
	/**
	 * ָ���̶���ʽ����
	 * @param fmt �� 
	 * @return
	 */
	public static String formatDateTime(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * ��ǰ������
	 * @return
	 */
	public static int getCurrentMonthLastDay(){
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);// ����������Ϊ���µ�һ��
		a.roll(Calendar.DATE, -1);// ���ڻع�һ�죬Ҳ�������һ��
		int maxDate = a.get(Calendar.DATE);
		return maxDate;

	}
	
	public static int getMonthLastDay(int year, int month) {

		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// ����������Ϊ���µ�һ��
		a.roll(Calendar.DATE, -1);// ���ڻع�һ�죬Ҳ�������һ��
		int maxDate = a.get(Calendar.DATE);
		return maxDate;

	}

}
