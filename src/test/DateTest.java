package test;

import java.util.*;
 

public class DateTest {
	
	
	public static void main(String  [] args){
		
//		 System.out.println(getCurrentMonthLastDay());
//		 
//		 
//		 Calendar cal = Calendar.getInstance();
//		 Date now = cal.getTime();
//		 int year = now.getYear();
//		 int month = now.getMonth();
//		 int day = now.getDay();
//		 System.out.println("year:"+(year+1900));
//		 System.out.println("month:"+(month+1));
//		 System.out.println("month:"+(day));
//		 
//		 int daynum = (int)Math.ceil(28/7.0f);
//		 System.out.println(daynum);
		
		Calendar cal = Calendar.getInstance();
		 Date now = cal.getTime();
		 int year = cal.get(Calendar.YEAR);
		 int month = cal.get(Calendar.MONTH)+1;
		 int day = cal.get(Calendar.DAY_OF_WEEK)-1;
		 int monthdays = getMonthLastDay(year, month);
		 
		 Calendar a = Calendar.getInstance();
		 
		 a.set(Calendar.YEAR, year);
		 a.set(Calendar.MONTH, month - 1);
		 a.set(Calendar.DATE, 1);
		 System.out.println(a.get(Calendar.DAY_OF_WEEK));
		 Date d = a.getTime();
		 System.out.println(d.getDay());
	}

	
	public static int getCurrentMonthLastDay()

	{

	Calendar a = Calendar.getInstance();

	a.set(Calendar.DATE, 1);//把日期设置为当月第一天

	a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天

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
