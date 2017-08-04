package org.aisno.core.mogilefs;


import java.util.Properties;
import java.io.IOException;


/**
 * 读取Properties文件的例子
 */
public class PropertiesUtils {
	static Properties prop = null;
	static String u = null;
	private static Properties myProperty = new Properties();
	
	/**
	 * 获取参数 
	 */
	public static String getProperty(String key){
		try {
			myProperty.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties"));
			if(null != key && !"".equals(key)){
				return myProperty.getProperty(key);
			}else{
				return "";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String getProperty(String key, String propertiesFilename){
		try {
			myProperty.load(PropertiesUtils.class.getClassLoader().getResourceAsStream(propertiesFilename));
			if(null != key && !"".equals(key)){
				return myProperty.getProperty(key);
			}else{
				return "";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
