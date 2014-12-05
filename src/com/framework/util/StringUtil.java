package com.framework.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class StringUtil {
	 

	/**
	 * ��ȡϵͳ��ˮ��
	 */
	public static String getUUID() {
		// replaceAll()֮�󷵻ص���һ����ʮ��������ʽ��ɵ��ҳ���Ϊ32���ַ���
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}

	/**
	 * �ж�������ַ��������Ƿ�Ϊ��
	 * 
	 * @return boolean ���򷵻�true,�ǿ���flase
	 */
	public static boolean isEmpty(String input) {
		return null == input || 0 == input.length()
				|| 0 == input.replaceAll("\\s", "").length();
	}

	/**
	 * �ж�������ֽ������Ƿ�Ϊ��
	 * 
	 * @return boolean ���򷵻�true,�ǿ���flase
	 */
	public static boolean isEmpty(byte[] bytes) {
		return null == bytes || 0 == bytes.length;
	}


	/**
	 * ���Ԫת��
	 * 
	 * @see ע��:�÷����ɴ���Ǫ�����ڵĽ��,������С��λ,����С��λ�ĳ���
	 * @see ע��:�����Ľ��ﵽ�˷�Ǫ������,���Ƽ�ʹ�ø÷���,�����������Ľ�������˴��һ��
	 * @param amount
	 *            ����Ԫ�����ַ���
	 * @return String ���ķֽ����ַ���
	 */
	public static String moneyYuanToFen(String amount) {
		if (isEmpty(amount)) {
			return amount;
		}
		// ����Ľ���ַ����������һ������
		if (-1 == amount.indexOf(".")) {
			return Integer.parseInt(amount) * 100 + "";
		}
		// ����Ľ���ַ������溬С����-->ȡС����ǰ����ַ���,����֮ת���ɵ�λΪ�ֵ�������ʾ
		int money_fen = Integer.parseInt(amount.substring(0,
				amount.indexOf("."))) * 100;
		// ȡ��С���������ַ���
		String pointBehind = (amount.substring(amount.indexOf(".") + 1));
		// amount=12.3
		if (pointBehind.length() == 1) {
			return money_fen + Integer.parseInt(pointBehind) * 10 + "";
		}
		// С�������ĵ�һλ�ַ�����������ʾ
		int pointString_1 = Integer.parseInt(pointBehind.substring(0, 1));
		// С�������ĵڶ�λ�ַ�����������ʾ
		int pointString_2 = Integer.parseInt(pointBehind.substring(1, 2));
		// amount==12.03,amount=12.00,amount=12.30
		if (pointString_1 == 0) {
			return money_fen + pointString_2 + "";
		} else {
			return money_fen + pointString_1 * 10 + pointString_2 + "";
		}
	}

	/**
	 * ���Ԫת��
	 * 
	 * @see �÷����Ὣ�����С����������ֵ,���������ֻ������λ....��12.345-->12.35
	 * @see ע��:�÷����ɴ���Ǫ�����ڵĽ��
	 * @see ע��:�����Ľ��ﵽ�˷�Ǫ������,��ǳ�������ʹ�ø÷���,�����������Ľ�������˴��һ��
	 * @param amount
	 *            ����Ԫ�����ַ���
	 * @return String ���ķֽ����ַ���
	 */
	public static String moneyYuanToFenByRound(String amount) {
		if (isEmpty(amount)) {
			return amount;
		}
		if (-1 == amount.indexOf(".")) {
			return Integer.parseInt(amount) * 100 + "";
		}
		int money_fen = Integer.parseInt(amount.substring(0,
				amount.indexOf("."))) * 100;
		String pointBehind = (amount.substring(amount.indexOf(".") + 1));
		if (pointBehind.length() == 1) {
			return money_fen + Integer.parseInt(pointBehind) * 10 + "";
		}
		int pointString_1 = Integer.parseInt(pointBehind.substring(0, 1));
		int pointString_2 = Integer.parseInt(pointBehind.substring(1, 2));
		// �������ַ�ʽ���ڴ���pointBehind=245,286,295,298,995,998����Ҫ������������
		if (pointBehind.length() > 2) {
			int pointString_3 = Integer.parseInt(pointBehind.substring(2, 3));
			if (pointString_3 >= 5) {
				if (pointString_2 == 9) {
					if (pointString_1 == 9) {
						money_fen = money_fen + 100;
						pointString_1 = 0;
						pointString_2 = 0;
					} else {
						pointString_1 = pointString_1 + 1;
						pointString_2 = 0;
					}
				} else {
					pointString_2 = pointString_2 + 1;
				}
			}
		}
		if (pointString_1 == 0) {
			return money_fen + pointString_2 + "";
		} else {
			return money_fen + pointString_1 * 10 + pointString_2 + "";
		}
	}

	/**
	 * ����תԪ
	 * 
	 * @see ע��:�������Ĳ����к�С����,��ֱ��ԭ������
	 * @see �÷������صĽ���ַ�����ʽΪ<code>00.00</code>,������λ����������һ��,С��λ���ҳ��ȹ̶�Ϊ2
	 * @param amount
	 *            ���ķֽ����ַ���
	 * @return String ����Ԫ�����ַ���
	 */
	public static String moneyFenToYuan(String amount) {
		if (isEmpty(amount)) {
			return amount;
		}
		if (amount.indexOf(".") > -1) {
			return amount;
		}
		if (amount.length() == 1) {
			return "0.0" + amount;
		} else if (amount.length() == 2) {
			return "0." + amount;
		} else {
			return amount.substring(0, amount.length() - 2) + "."
					+ amount.substring(amount.length() - 2);
		}
	}

	/**
	 * �ֽ�����תΪ�ַ���
	 * 
	 * @see �÷���Ĭ����ISO-8859-1ת��
	 * @see �����Լ�ָ���ַ���,����ʹ��<code>getString(byte[] data, String charset)</code>����
	 */
	public static String getString(byte[] data) {
		return getString(data, "ISO-8859-1");
	}

	/**
	 * �ֽ�����תΪ�ַ���
	 * 
	 * @see ���ϵͳ��֧���������<code>charset</code>�ַ���,����ϵͳĬ���ַ�������ת��
	 */
	public static String getString(byte[] data, String charset) {
		if (isEmpty(data)) {
			return "";
		}
		if (isEmpty(charset)) {
			return new String(data);
		}
		try {
			return new String(data, charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new String(data);
		}
	}

	/**
	 * ��ȡһ���ַ����ļ���Ч��
	 * 
	 * @return String ���ص��ַ�����ʽ������"abcd***hijk"
	 */
	public static String getStringSimple(String data) {
		return data.substring(0, 4) + "***" + data.substring(data.length() - 4);
	}

	/**
	 * �ַ���תΪ�ֽ�����
	 * 
	 * @see �÷���Ĭ����ISO-8859-1ת��
	 * @see �����Լ�ָ���ַ���,����ʹ��<code>getBytes(String str, String charset)</code>����
	 */
	public static byte[] getBytes(String data) {
		return getBytes(data, "ISO-8859-1");
	}

	/**
	 * �ַ���תΪ�ֽ�����
	 * 
	 * @see ���ϵͳ��֧���������<code>charset</code>�ַ���,����ϵͳĬ���ַ�������ת��
	 */
	public static byte[] getBytes(String data, String charset) {
		data = (data == null ? "" : data);
		if (isEmpty(charset)) {
			return data.getBytes();
		}
		try {
			return data.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
		 
			return data.getBytes();
		}
	}

	/**
	 * �ַ�����
	 * 
	 * @see �÷���Ĭ�ϻ���UTF-8�����ַ���
	 * @see �����Լ�ָ���ַ���,����ʹ��<code>encode(String chinese, String charset)</code>����
	 */
	public static String encode(String chinese) {
		return encode(chinese, "UTF-8");
	}

	/**
	 * �ַ�����
	 * 
	 * @see �÷���ͨ�����ڶ����Ľ��б���
	 * @see ��ϵͳ��֧��ָ���ı����ַ���,��ֱ�ӽ�<code>chinese</code>ԭ������
	 */
	public static String encode(String chinese, String charset) {
		chinese = (chinese == null ? "" : chinese);
		try {
			return URLEncoder.encode(chinese, charset);
		} catch (UnsupportedEncodingException e) {
//			LogUtil.getLogger().error(
//					"�����ַ���[" + chinese + "]ʱ�����쳣:ϵͳ��֧�ָ��ַ���[" + charset + "]");
			return chinese;
		}
	}

	/**
	 * �ַ�����
	 * 
	 * @see �÷���Ĭ�ϻ���UTF-8�����ַ���
	 * @see �����Լ�ָ���ַ���,����ʹ��<code>decode(String chinese, String charset)</code>����
	 */
	public static String decode(String chinese) {
		return decode(chinese, "UTF-8");
	}

	/**
	 * �ַ�����
	 * 
	 * @see �÷���ͨ�����ڶ����Ľ��н���
	 * @see ��ϵͳ��֧��ָ���Ľ����ַ���,��ֱ�ӽ�<code>chinese</code>ԭ������
	 */
	public static String decode(String chinese, String charset) {
		chinese = (chinese == null ? "" : chinese);
		try {
			return URLDecoder.decode(chinese, charset);
		} catch (UnsupportedEncodingException e) {
			return chinese;
		}
	}

	

	/**
	 * HTML�ַ�ת��
	 * 
	 * @see ����������е������ַ����й����滻,��ֹ�û�����JavaScript�ȷ�ʽ����������
	 * @see String input = <img src='http://t1.baidu.com/it/fm=0&gp=0.jpg'/>
	 * @see HtmlUtils.htmlEscape(input); //from spring.jar
	 * @see StringEscapeUtils.escapeHtml(input); //from commons-lang.jar
	 * @see ����Spring��Apache���ṩ���ַ�ת��ķ���,��Apache��StringEscapeUtils����Ҫ��ǿ��һЩ
	 * @see StringEscapeUtils�ṩ�˶�HTML,Java,JavaScript,SQL,XML���ַ���ת��ͷ�ת��
	 * @see ��������ת��HTML�ַ�ʱ,������Ե����źͿո����ת��,�����������ṩ�˶����ǵ�ת��
	 * @return String ���˺���ַ���
	 */
	public static String htmlEscape(String input) {
		if (isEmpty(input)) {
			return input;
		}
		input = input.replaceAll("&", "&amp;");
		input = input.replaceAll("<", "&lt;");
		input = input.replaceAll(">", "&gt;");
		input = input.replaceAll(" ", "&nbsp;");
		input = input.replaceAll("'", "&#39;"); // IE�ݲ�֧�ֵ����ŵ�ʵ������,��֧�ֵ����ŵ�ʵ����,�ʵ�����ת���ʵ����,�����ַ�ת���ʵ������
		input = input.replaceAll("\"", "&quot;"); // ˫����Ҳ��Ҫת�壬���Լ�һ��б�߶������ת��
		input = input.replaceAll("\n", "<br/>"); // ���ܰ�\n�Ĺ��˷���ǰ�棬��Ϊ��Ҫ��<��>���ˣ������ͻᵼ��<br/>ʧЧ��
		return input;
	}
}
