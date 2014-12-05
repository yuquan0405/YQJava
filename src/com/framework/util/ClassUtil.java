package com.framework.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import com.framework.exception.BussinessException;
 

import test.TestBean;

public class ClassUtil {
	
	/**
	 * ��ȡ�������Ի���
	 */
	public static Hashtable<String,List<String>> classFileds = new Hashtable<String, List<String>>();
	
	/**
	 * ���䷽������ӡ��������ԣ�����������������
	 * @param obj ���������
	 */
	public static void reflect(Object obj) {
		Class<?> cls = obj.getClass();
		System.out.println("************��  ��  ��************");
		Constructor<?>[] constructors = cls.getConstructors();
		for (Constructor<?> constructor : constructors) {
			System.out.println("����������:" + constructor.getName() + "\t"+ "    "
					+ "��������������:"
					+ Arrays.toString(constructor.getParameterTypes()));
		}
		System.out.println("************��     ��************");
		Field[] fields = cls.getDeclaredFields();
		// cls.getFields() �÷���ֻ�ܷ��ʹ��е�����
		// cls.getDeclaredFields()  ���Է���˽������
		for (Field field : fields) {
			System.out.println("��������:" + field.getName() + "\t"+ "��������:"
					+ field.getType()+"\t");
		}
		System.out.println("************��   ��************");
		Method[] methods = cls.getMethods();
		for (Method method : methods) {
			System.out.println("������:" + method.getName() + "\t" + "�����������ͣ�"
					+ method.getReturnType() + "\t"+ "������������:"
					+ Arrays.toString(method.getParameterTypes()));
		}
	}
	
	/**
	 * 
	 * @param obj ���ʶ���
	 * @param filedname  ���������
	 * @return ���ض��������ֵ
	 * @throws Exception
	 */
	public static Object getFieldValue(Object obj,String filedname) {
		//���������
		Class<?> clazz = obj.getClass();
		//����������ֶ�
		
		//��ȡ����ʱ��ѹ��Java�Է������η��ļ�� 
		/*�������Ե���*/ 
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
		//�ڶ���obj�϶�ȡfield���Ե�ֵ
		Object val = null;
		try {
			val = field.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return val;
	}
	
	public static void setFieldValue(Object obj,String filedname,Object filedvalue) {
		//���������
		Class<?> clazz = obj.getClass();
		//����������ֶ�
		Field field = null;// clazz.getDeclaredField(filedname);
		//��ȡ����ʱ��ѹ��Java�Է������η��ļ�� 
		
		/*�������Ե���*/ 
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
		//�ڶ���obj�϶�ȡfield���Ե�ֵ
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
	 * ������ö���ķ���
	 * @param obj ���� 
	 * @param methodName  �������� 
	 * @param paramType ��������    new Class[]{int.class,double.class}
	 * @param params ����ֵ     new Object[]{2,3.5}
	 * @return
	 * @throws Exception 
	 */
	public static Object readObjMethod(Object obj,String methodName,Class<?>[] paramTypes,Object[] params) throws  Exception{
		//��������
		Class<?> clazz = obj.getClass();
		//���ַ���
		Method method = clazz.getDeclaredMethod(methodName, paramTypes);
		//���ʷ���ʱ,ѹ��Java�Է������η��ļ��
		
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
