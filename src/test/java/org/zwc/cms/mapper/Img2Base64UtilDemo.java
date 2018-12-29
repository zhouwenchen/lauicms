package org.zwc.cms.mapper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;

/**
 * 将图片转换为Base64<br>
 * 将base64编码字符串解码成img图片
 */
public class Img2Base64UtilDemo {

	public static void main(String[] args) {
		String imgFile = "e:/test/1.jpg";// 待处理的图片
		String imgbese = getImgStr(imgFile);
//		System.out.println(imgbese.length());
		System.out.println(imgbese);
		
		
//		String imgbese = "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";
		String imgFilePath = "e:/test/2.jpg";
		boolean b = generateImage(imgbese, imgFilePath);
		
		
		
		
		System.out.println(b);
	}

	/**
	 * 将图片转换成Base64编码
	 * 
	 * @param imgFile
	 *            待处理图片
	 * @return
	 */
	public static String getImgStr(String imgFile) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理

		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(Base64.encodeBase64(data));
	}

	/**
	 * 对字节数组字符串进行Base64解码并生成图片
	 * 
	 * @param imgStr
	 *            图片数据
	 * @param imgFilePath
	 *            保存图片全路径地址
	 * @return
	 */
	public static boolean generateImage(String imgStr, String imgFilePath) {
		if (imgStr == null) // 图像数据为空
			return false;
		try {
			// Base64解码
			byte[] b = Base64.decodeBase64(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}

			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}