package com.framework.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 获取前一天日期yyyyMMdd
	 * 
	 * @see 经测试，针对闰年02月份或跨年等情况，该代码仍有效。测试代码如下
	 * @see calendar.set(Calendar.YEAR, 2013);
	 * @see calendar.set(Calendar.MONTH, 0);
	 * @see calendar.set(Calendar.DATE, 1);
	 * @see 测试时，将其放到<code>calendar.add(Calendar.DATE, -1);</code>前面即可
	 * @return 返回的日期格式为yyyyMMdd
	 */
	public static String getYestoday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
	}

	/**
	 * 获取当前的日期yyyyMMdd
	 */
	public static String getCurrentDate() {
		return getCurrentDateTime("yyyyMMdd");
	}

	/**
	 * 获取当前的时间yyyyMMddHHmmss
	 */
	public static String getCurrentTime() {
		return getCurrentDateTime("yyyyMMddHHmmss");
	}
	/**
	 * 获取当前的时间yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDateTime() {
		return getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 指定格式当前日期
	 * @param fmt 如 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentDateTime(String fmt) {
		return new SimpleDateFormat(fmt).format(new Date());
	}
	
	/**
	 * 指定格式日期
	 * @param fmt 如 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatDateTime(Date date,String fmt) {
		return new SimpleDateFormat(fmt).format(date);
	}
	
	/**
	 * 指定固定格式日期
	 * @param fmt 如 
	 * @return
	 */
	public static String formatDateTime(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 当前月天数
	 * @return
	 */
	public static int getCurrentMonthLastDay(){
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;

	}
	
	public static int getMonthLastDay(int year, int month) {

		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;

	}

}
