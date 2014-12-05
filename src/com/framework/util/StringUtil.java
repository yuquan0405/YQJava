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
	 * 获取系统流水号
	 */
	public static String getUUID() {
		// replaceAll()之后返回的是一个由十六进制形式组成的且长度为32的字符串
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}

	/**
	 * 判断输入的字符串参数是否为空
	 * 
	 * @return boolean 空则返回true,非空则flase
	 */
	public static boolean isEmpty(String input) {
		return null == input || 0 == input.length()
				|| 0 == input.replaceAll("\\s", "").length();
	}

	/**
	 * 判断输入的字节数组是否为空
	 * 
	 * @return boolean 空则返回true,非空则flase
	 */
	public static boolean isEmpty(byte[] bytes) {
		return null == bytes || 0 == bytes.length;
	}


	/**
	 * 金额元转分
	 * 
	 * @see 注意:该方法可处理贰仟万以内的金额,且若有小数位,则不限小数位的长度
	 * @see 注意:如果你的金额达到了贰仟万以上,则不推荐使用该方法,否则计算出来的结果会令人大吃一惊
	 * @param amount
	 *            金额的元进制字符串
	 * @return String 金额的分进制字符串
	 */
	public static String moneyYuanToFen(String amount) {
		if (isEmpty(amount)) {
			return amount;
		}
		// 传入的金额字符串代表的是一个整数
		if (-1 == amount.indexOf(".")) {
			return Integer.parseInt(amount) * 100 + "";
		}
		// 传入的金额字符串里面含小数点-->取小数点前面的字符串,并将之转换成单位为分的整数表示
		int money_fen = Integer.parseInt(amount.substring(0,
				amount.indexOf("."))) * 100;
		// 取到小数点后面的字符串
		String pointBehind = (amount.substring(amount.indexOf(".") + 1));
		// amount=12.3
		if (pointBehind.length() == 1) {
			return money_fen + Integer.parseInt(pointBehind) * 10 + "";
		}
		// 小数点后面的第一位字符串的整数表示
		int pointString_1 = Integer.parseInt(pointBehind.substring(0, 1));
		// 小数点后面的第二位字符串的整数表示
		int pointString_2 = Integer.parseInt(pointBehind.substring(1, 2));
		// amount==12.03,amount=12.00,amount=12.30
		if (pointString_1 == 0) {
			return money_fen + pointString_2 + "";
		} else {
			return money_fen + pointString_1 * 10 + pointString_2 + "";
		}
	}

	/**
	 * 金额元转分
	 * 
	 * @see 该方法会将金额中小数点后面的数值,四舍五入后只保留两位....如12.345-->12.35
	 * @see 注意:该方法可处理贰仟万以内的金额
	 * @see 注意:如果你的金额达到了贰仟万以上,则非常不建议使用该方法,否则计算出来的结果会令人大吃一惊
	 * @param amount
	 *            金额的元进制字符串
	 * @return String 金额的分进制字符串
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
		// 下面这种方式用于处理pointBehind=245,286,295,298,995,998等需要四舍五入的情况
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
	 * 金额分转元
	 * 
	 * @see 注意:如果传入的参数中含小数点,则直接原样返回
	 * @see 该方法返回的金额字符串格式为<code>00.00</code>,其整数位有且至少有一个,小数位有且长度固定为2
	 * @param amount
	 *            金额的分进制字符串
	 * @return String 金额的元进制字符串
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
	 * 字节数组转为字符串
	 * 
	 * @see 该方法默认以ISO-8859-1转码
	 * @see 若想自己指定字符集,可以使用<code>getString(byte[] data, String charset)</code>方法
	 */
	public static String getString(byte[] data) {
		return getString(data, "ISO-8859-1");
	}

	/**
	 * 字节数组转为字符串
	 * 
	 * @see 如果系统不支持所传入的<code>charset</code>字符集,则按照系统默认字符集进行转换
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
	 * 获取一个字符串的简明效果
	 * 
	 * @return String 返回的字符串格式类似于"abcd***hijk"
	 */
	public static String getStringSimple(String data) {
		return data.substring(0, 4) + "***" + data.substring(data.length() - 4);
	}

	/**
	 * 字符串转为字节数组
	 * 
	 * @see 该方法默认以ISO-8859-1转码
	 * @see 若想自己指定字符集,可以使用<code>getBytes(String str, String charset)</code>方法
	 */
	public static byte[] getBytes(String data) {
		return getBytes(data, "ISO-8859-1");
	}

	/**
	 * 字符串转为字节数组
	 * 
	 * @see 如果系统不支持所传入的<code>charset</code>字符集,则按照系统默认字符集进行转换
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
	 * 字符编码
	 * 
	 * @see 该方法默认会以UTF-8编码字符串
	 * @see 若想自己指定字符集,可以使用<code>encode(String chinese, String charset)</code>方法
	 */
	public static String encode(String chinese) {
		return encode(chinese, "UTF-8");
	}

	/**
	 * 字符编码
	 * 
	 * @see 该方法通常用于对中文进行编码
	 * @see 若系统不支持指定的编码字符集,则直接将<code>chinese</code>原样返回
	 */
	public static String encode(String chinese, String charset) {
		chinese = (chinese == null ? "" : chinese);
		try {
			return URLEncoder.encode(chinese, charset);
		} catch (UnsupportedEncodingException e) {
//			LogUtil.getLogger().error(
//					"编码字符串[" + chinese + "]时发生异常:系统不支持该字符集[" + charset + "]");
			return chinese;
		}
	}

	/**
	 * 字符解码
	 * 
	 * @see 该方法默认会以UTF-8解码字符串
	 * @see 若想自己指定字符集,可以使用<code>decode(String chinese, String charset)</code>方法
	 */
	public static String decode(String chinese) {
		return decode(chinese, "UTF-8");
	}

	/**
	 * 字符解码
	 * 
	 * @see 该方法通常用于对中文进行解码
	 * @see 若系统不支持指定的解码字符集,则直接将<code>chinese</code>原样返回
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
	 * HTML字符转义
	 * 
	 * @see 对输入参数中的敏感字符进行过滤替换,防止用户利用JavaScript等方式输入恶意代码
	 * @see String input = <img src='http://t1.baidu.com/it/fm=0&gp=0.jpg'/>
	 * @see HtmlUtils.htmlEscape(input); //from spring.jar
	 * @see StringEscapeUtils.escapeHtml(input); //from commons-lang.jar
	 * @see 尽管Spring和Apache都提供了字符转义的方法,但Apache的StringEscapeUtils功能要更强大一些
	 * @see StringEscapeUtils提供了对HTML,Java,JavaScript,SQL,XML等字符的转义和反转义
	 * @see 但二者在转义HTML字符时,都不会对单引号和空格进行转义,而本方法则提供了对它们的转义
	 * @return String 过滤后的字符串
	 */
	public static String htmlEscape(String input) {
		if (isEmpty(input)) {
			return input;
		}
		input = input.replaceAll("&", "&amp;");
		input = input.replaceAll("<", "&lt;");
		input = input.replaceAll(">", "&gt;");
		input = input.replaceAll(" ", "&nbsp;");
		input = input.replaceAll("'", "&#39;"); // IE暂不支持单引号的实体名称,而支持单引号的实体编号,故单引号转义成实体编号,其它字符转义成实体名称
		input = input.replaceAll("\"", "&quot;"); // 双引号也需要转义，所以加一个斜线对其进行转义
		input = input.replaceAll("\n", "<br/>"); // 不能把\n的过滤放在前面，因为还要对<和>过滤，这样就会导致<br/>失效了
		return input;
	}
}
