package com.framework.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import com.framework.exception.BussinessException;
 

import test.TestBean;

public class ClassUtil {
	
	/**
	 * 读取对象属性缓存
	 */
	public static Hashtable<String,List<String>> classFileds = new Hashtable<String, List<String>>();
	
	/**
	 * 反射方法，打印对象的属性，方法，构造器属性
	 * @param obj 被反射对象
	 */
	public static void reflect(Object obj) {
		Class<?> cls = obj.getClass();
		System.out.println("************构  造  器************");
		Constructor<?>[] constructors = cls.getConstructors();
		for (Constructor<?> constructor : constructors) {
			System.out.println("构造器名称:" + constructor.getName() + "\t"+ "    "
					+ "构造器参数类型:"
					+ Arrays.toString(constructor.getParameterTypes()));
		}
		System.out.println("************属     性************");
		Field[] fields = cls.getDeclaredFields();
		// cls.getFields() 该方法只能访问共有的属性
		// cls.getDeclaredFields()  可以访问私有属性
		for (Field field : fields) {
			System.out.println("属性名称:" + field.getName() + "\t"+ "属性类型:"
					+ field.getType()+"\t");
		}
		System.out.println("************方   法************");
		Method[] methods = cls.getMethods();
		for (Method method : methods) {
			System.out.println("方法名:" + method.getName() + "\t" + "方法返回类型："
					+ method.getReturnType() + "\t"+ "方法参数类型:"
					+ Arrays.toString(method.getParameterTypes()));
		}
	}
	
	/**
	 * 
	 * @param obj 访问对象
	 * @param filedname  对象的属性
	 * @return 返回对象的属性值
	 * @throws Exception
	 */
	public static Object getFieldValue(Object obj,String filedname) {
		//反射出类型
		Class<?> clazz = obj.getClass();
		//反射出类型字段
		
		//获取属性时，压制Java对访问修饰符的检查 
		/*父级属性调用*/ 
		Field field = null; //clazz.getDeclaredField(filedname);
		while (clazz != null) {
			try {
				field = clazz.getDeclaredField(filedname);
			} catch (NoSuchFieldException e) {
				
			}
			if (field != null) {
				break;
			}
			clazz = clazz.getSuperclass();
		}
		
		field.setAccessible(true);
		//在对象obj上读取field属性的值
		Object val = null;
		try {
			val = field.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return val;
	}
	
	public static void setFieldValue(Object obj,String filedname,Object filedvalue) {
		//反射出类型
		Class<?> clazz = obj.getClass();
		//反射出类型字段
		Field field = null;// clazz.getDeclaredField(filedname);
		//获取属性时，压制Java对访问修饰符的检查 
		
		/*父级属性调用*/ 
		while (clazz != null) {
			try {
				field = clazz.getDeclaredField(filedname);
			} catch (NoSuchFieldException e) {
				
			}
			if (field != null) {
				break;
			}
			clazz = clazz.getSuperclass();
		}
		
		field.setAccessible(true);
		//在对象obj上读取field属性的值
		try {
			field.set(obj, filedvalue);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static List<String> getFields(Object obj){
		
		List<String> list = null;
		String cname = obj.getClass().getName();
		if(classFileds.containsKey(cname)){
			list = classFileds.get(cname);
		}else{
			list = new ArrayList<String>();
			Class<?> clazz = obj.getClass();
			Field[] field = null;
			field = null;
			field = clazz.getDeclaredFields();
			for(int i = 0; i < field.length; i++){
				if(!list.contains(field[i].getName())){
					list.add(field[i].getName());
				}
			}
			classFileds.put(cname, list);
		}
		
		return list;		
	}
	
	/**
	 * 反射调用对象的方法
	 * @param obj 对象 
	 * @param methodName  方法名称 
	 * @param paramType 参数类型    new Class[]{int.class,double.class}
	 * @param params 参数值     new Object[]{2,3.5}
	 * @return
	 * @throws Exception 
	 */
	public static Object readObjMethod(Object obj,String methodName,Class<?>[] paramTypes,Object[] params) throws  Exception{
		//发现类型
		Class<?> clazz = obj.getClass();
		//发现方法
		Method method = clazz.getDeclaredMethod(methodName, paramTypes);
		//访问方法时,压制Java对访问修饰符的检查
		
		method.setAccessible(true);
		Object val = method.invoke(obj, params);
		return val;
	}
	
	public static void main(String [] args){
//		Object[] o = new String[]{"1","2","3"};
//		ClassUtil.test(new String[]{"1","2","3"});
//		ClassUtil.test("1","2","3");
//		ClassUtil.test(o);
		
		
		try {
			TestBean test = new TestBean();
			System.out.println(test.getClass().getName());
			test.setId("123");
			long btime = (new Date()).getTime();
			
			for(int i = 0 ; i < 10000; i++){
				System.out.println(ClassUtil.getFields(test));
			}
			
			//System.out.println(ClassUtil.getFields(test));
			long etime = (new Date()).getTime();
			System.out.println("time:"+(etime-btime));
//			ClassUtil.reflect(test);
//			ClassUtil.setFieldValue(test, "id","zzzz");
//		ClassUtil.getFieldValue(test, "id", ");
			
//			System.out.println(test.getId());
			
			//System.out.println(ClassUtil.getFieldValue(test, "id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
