package org.zwc.cms.utils;

/**
 * 
 * maintype中文与英文的转化
 * 
 * @author zhouwenchen
 *
 * @date 2019年2月1日 下午5:24:52
 */
public class GetMainType {
	public static String  getMain(String mainType) {
		String mainStr = "";
		switch (mainType) {
		case "汽车":
			mainStr = "qiche";
			break;

		default:
			break;
		}
		return mainStr;
	}
}
