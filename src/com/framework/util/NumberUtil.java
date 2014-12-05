package com.framework.util;

public class NumberUtil {
	
	/**
	 * �Ƿ�Ϊ�����ж�(����������,�򸡵�������)
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		boolean flag = true;
		if(str == null){
			flag = false;
			return flag;
		}
		for (int i = str.length(); --i >= 0;) {
			int chr = str.charAt(i);
			if(chr == 46 || (i == 0 && chr == 45)){
				continue;
			}
			if (chr < 48 || chr > 57) {
				
				flag = false;
				break;
			}

		}
		return flag;
	}
	/**
	 * �Ƿ�Ϊ��������ƥ��
	 * @param str
	 * @return
	 */
	public static boolean isNumberMatches(String str){
		boolean flag = true;
		if(str == null){
			flag = false;
			return flag;
		}
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
	public static void main(String [] args){
		int i = '.';
		System.out.println(i);
		System.out.println(NumberUtil.isNumber("24ssddddddddddddd3"));
		
	}

}
