package test;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.framework.dao.IJdbcDao;
import com.framework.dao.JdbcDao;

public class AppTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AppTest.daoTest();

//		ApplicationContext app = new FileSystemXmlApplicationContext("D:\\work_2013\\YQJava\\src\\conf\\applicationContext-common.xml");
//				
//		JdbcDao dao = (JdbcDao)app.getBean("jdbcDao");		
//
//		List<TestBean> lst = dao.queryToObject(TestBean.class, "");
//
//
//		List<TestBean> lst2 = AppTest.queryToObject(TestBean.class, "");
//		
//		System.out.println(dao.getJdbcType());
//		
//		
//		
//		long btime = (new Date()).getTime();
//		//String sql =  "select xh as uname,kcmc as testDemo from atest";
//		String sql = "select * from test";
//		for(int i = 0; i < 0; i++){
//			//System.out.println(i);
//			//List<TestBean> lst  = dao.queryToObject(TestBean.class,, null);
//			//List lst2 = dao.queryToArray(sql);
//			List lst3 = dao.queryToMap(sql);
//			System.out.println(lst3);
//		}
//		
////		for(TestBean t : lst){
////			System.out.println(t.getUname()+":"+t.getTestDemo());
////		}
//		long etime = (new Date()).getTime();
//		System.out.println("end!"+(etime - btime));
	}
	
	public static void daoTest(){
		
			
		
		try {
			ApplicationContext app = new FileSystemXmlApplicationContext("D:\\work\\YQJava\\src\\conf\\applicationContext-common.xml");
			
			JdbcDao dao = (JdbcDao)app.getBean("jdbcDao");	
			dao.queryToArray("update aa set a1 = '88888' ");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

//		List<TestBean> lst = dao.queryToObject(TestBean.class, "select * from testbean");
//		for(TestBean t :lst){
//			System.out.println("uname:"+t.getUname());
//		}
//		System.out.println(lst.size());
//		List<Map<String,String>> lstMap = dao.queryToMap("select * from testbean");
//		
//		for(Map<String,String> map : lstMap){
//			String str = map.get("uid");
//			if(str == null){
//				System.out.println("uid is null");
//			}else{
//				System.out.println(str);
//			}
//			
//		}
//		long beginTime  = (new Date()).getTime();
//		for(int i = 0; i < 10000; i++){
//			System.out.println(i);
//			TestBean b = new TestBean();
//			b.setUid(String.valueOf(i));
//			b.setUname("testname_"+i);
//			b.setAge(99);
//			b.setPwd("123456");
//			dao.save(b);
//		}		
//		long endTime  = (new Date()).getTime();
//		System.out.println(endTime - beginTime);
//		TestBean a = dao.loadObject(b);
//		System.out.println("uname:"+a.getUname());
		//dao.delete(b);
		
//		TestBean b = new TestBean();
//		b.setUid("652b40f3-cba1-11e3-8873-53dc55187607");
//		b.setUname("testname_");
//		b.setAge(null);
////		b.setPwd("888");
//		dao.update(b, true);
	}
	
	public static <T> List<T> queryToObject(Class<T> cls,String sql){

		return null;
	}

}
