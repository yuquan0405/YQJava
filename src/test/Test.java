package test;

import java.io.File;
import java.util.Date;

import com.framework.util.NumberUtil;

public class Test {
	
	public Test() {
		System.out.println("2");
	}
	{
		System.out.println("1");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Test.testTime();
		
		
	}
	
	public static void testTime(){
		
		Date d1 = new Date();
		for (int i = 0; i < 10000000; i++) {
			try {
				System.out.println(NumberUtil.isNumber("24ssddddddddddddd3"));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		Date d2 = new Date();
		for (int i = 0; i < 10000000; i++) {
			try {
				System.out.println(NumberUtil.isNumberMatches("24ssddddddddddddd3"));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		Date d4 = new Date();
		System.out.println("time1:"+d1.getTime());
		System.out.println("time2:"+d2.getTime());
		System.out.println("1:"+(d2.getTime() - d1.getTime()));
		
	}
	public static void FilePathTest(){
		try{
			String str = "/mains/updata/pxndpxjh/20134017001.zip";
			String s = File.separator+File.separator;
			System.out.println(s);
			str = str.replaceAll("/", s);
			System.out.println(str);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
