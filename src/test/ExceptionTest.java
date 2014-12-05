package test;

import java.util.Date;

import com.framework.exception.BussinessException;

public class ExceptionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date d1 = new Date();
		for (int i = 0; i < 90000; i++) {
			try {
				ExceptionTest t1 = new ExceptionTest();
				t1.test1();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		Date d2 = new Date();
		for (int i = 0; i < 90000; i++) {
			try {
				ExceptionTest t1 = new ExceptionTest();
				t1.test2();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		Date d3 = new Date();
		for (int i = 0; i < 90000; i++) {
			ExceptionTest t1 = new ExceptionTest();
			t1.test3();
		}
		Date d4 = new Date();
		System.out.println("1:"+(d2.getTime() - d1.getTime()));
		System.out.println("2:"+(d3.getTime() - d2.getTime()));
		System.out.println("3:"+(d4.getTime() - d3.getTime()));
	}

	
	public void test1() throws Exception{
		
		String a = "123b";
		int b = 0;
		if(a.indexOf("b") >= 0){
			throw new Exception("ÎÞÐ§Êý×Ö");
		}
		b = Integer.parseInt(a);
		
	}
	
	
	public void test2()  {
		
		String a = "123b";
		int b = 0;
		b = Integer.parseInt(a);
		
	}
	
public void test3()  {
		
		String a = "123";
		int b = 0;
		b = Integer.parseInt(a);
		
	}
}
