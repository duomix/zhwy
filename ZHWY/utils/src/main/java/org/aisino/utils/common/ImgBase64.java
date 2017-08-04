package org.aisino.utils.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import net.iharder.Base64;

/**
 *  java实现图片与base64字符串之间的转换 
 * @author root
 */
public class ImgBase64 {
	
	/*public static void main(String[] args) {
		String strImg = GetImageStr("/home/yzx/公共的/xz.png");
		System.out.println(strImg);
		GenerateImage(strImg,"/home/asd.png");
	}*/

	/**
	 * 图片转化成base64字符串
	 * @param imgFile  图片路径
	 * @return
	 */
	public static String GetImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		String imgbase = null;
		InputStream in = null;
		try {
			in = new FileInputStream(imgFile);
			imgbase = GetImageStr(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgbase;
	}
	/**
	 * @param in  图片流
	 * @return
	 */
	public static String GetImageStr(InputStream in) {
		byte[] data = null;
		// 读取图片字节数组
		try {
			if(in == null){
				return null;
			}
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		return new String(Base64.encodeBytes(data));// 返回Base64编码过的字节数组字符串
	}

	
	/**
	 * base64字符串转化成图片
	 * @param imgStr   图片base64编码
	 * @param imgFilePath  图片保存路径
	 * @return
	 */
	public static boolean GenerateImage(String imgStr,String imgFilePath) {
		if (imgStr == null || imgFilePath == null) // 图像数据为空
			return false;
		try {
			// Base64解码
			byte[] b = Base64.decode(imgStr);
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
