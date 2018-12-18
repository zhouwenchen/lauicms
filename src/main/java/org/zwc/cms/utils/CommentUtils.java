package org.zwc.cms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**  
 *  公共类
* @author zhouwenchen
* @date 2018年12月17日 下午4:45:15 
*/  
public class CommentUtils {
	
	public static String formatNewsId(long rowkey, String max){
		String result = "";
		String random = getRandom(max);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		result = format.format(new Date(rowkey)) + random;
		return result;
	}

	/**
	 * 对rowkey后面加上随机数 
	 */
	public static String builderRowKey(String rowkey, String max){
		String result = "";
		result = rowkey + getRandom(max);
		return result;
	}
	
	/**
	 * 对rowkey格式化 
	 */
	public static String formatRowKey(String rowkey, String max){
		String result = "";
		String random = getRandom(max);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		result = format.format(new Date(Long.parseLong(rowkey))) + random;
		return result;
	}
	
	/**
	 * 获取随机数，位数不足在前面补0
	 */
	public static String getRandom(String max){
		Random r = new Random();
		String result = Integer.toString(r.nextInt(Integer.parseInt(max)));
		StringBuffer sb = new StringBuffer();
		for(int i = result.length(); i < max.length(); i ++){
			sb.append("0");
		}
		sb.append(result);
		return sb.toString();
	}
	
	public static void main(String[] args){
		String str = builderRowKey(Long.toString(new Date().getTime()), "999999");
		System.out.println(str.length());
		int i = Long.toString(Long.MAX_VALUE).length();
		System.out.println(i);
	}
}
