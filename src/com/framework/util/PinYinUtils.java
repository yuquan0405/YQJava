package com.framework.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtils {
//	 国标码和区位码转换常量
	static final int GB_SP_DIFF = 160;
//	存放国标一级汉字不同读音的起始区位码
	static final int[] secPosValueList = {
	1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787,
	3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086,
	4390, 4558, 4684, 4925, 5249, 5600};

//	存放国标一级汉字不同读音的起始区位码对应读音
	static final char[] firstLetter = {
	'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j',
	'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
	't', 'w', 'x', 'y', 'z'};
	/**
	 * 将字符串中的中文转化为拼音,其他字符不变
	 * 
	 * @param inputString
	 * @return
	 */
	public static String getPingYin(String inputString) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);

		char[] input = inputString.trim().toCharArray();
		String output = "";

		try {
			for (int i = 0; i < input.length; i++) {
				if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
					output += temp[0];
				} else
					output += java.lang.Character.toString(input[i]);
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return output;
	}

//	获取一个字符串的拼音码
	public static String getFirstLetter(String oriStr) {
		String str = oriStr.toLowerCase();
		StringBuffer buffer = new StringBuffer();
		char ch;
		char[] temp;
		for (int i = 0; i < str.length(); i++) { //依次处理str中每个字符
			ch = str.charAt(i);
			temp = new char[] {ch};
			byte[] uniCode = new String(temp).getBytes();
			if (uniCode[0] < 128 && uniCode[0] > 0) { // 非汉字
				buffer.append(temp);
			} else {
				buffer.append(convert(uniCode));
			}
		}
		return buffer.toString().toUpperCase();
	}

	/** 获取一个汉字的拼音首字母。
	* GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
	* 例如汉字“你”的GB码是0xC4/0xE3，分别减去0xA0（160）就是0x24/0x43
	* 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n’
	*/

	static char convert(byte[] bytes) {

		char result = '-';
		int secPosValue = 0;
		int i;
		for (i = 0; i < bytes.length; i++) {
			bytes[i] -= GB_SP_DIFF;
			}
			secPosValue = bytes[0] * 100 + bytes[1];
			for (i = 0; i < 23; i++) {
				if (secPosValue >= secPosValueList[i] && secPosValue < secPosValueList[i + 1]) {
				result = firstLetter[i];
				break;
			}
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(PinYinUtils.getPingYin("软件包"));
	}
}
