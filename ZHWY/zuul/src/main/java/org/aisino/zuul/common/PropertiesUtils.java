package org.aisino.zuul.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
	public PropertiesUtils() {
	}

	private static PropertiesUtils prop = null;
	private static String propPos = "";

	public static PropertiesUtils getInstance(String propPos) {
		PropertiesUtils.propPos = propPos;
		if (null == prop) {
			return new PropertiesUtils();
		} else {
			return prop;
		}
	}

	private InputStream is;
	private Properties props;

	private void getInputStream() {
		is = PropertiesUtils.class.getResourceAsStream("/"
				+ PropertiesUtils.propPos);
	}

	private Properties getProperties(InputStream is) {
		props = new Properties();
		try {
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	public String getProperty(String property) {
		getInputStream();
		Properties props = getProperties(is);
		return props.getProperty(property);
	}
	
	/**
	 * 获取工程文件流
	 * @param filepath
	 * @return
	 */
	public InputStream getFileInputStream(String filepath) {
		InputStream is = null;
		if(filepath != null && !"".equals(filepath)){
			is = PropertiesUtils.class.getResourceAsStream("/" + filepath);
		}
		return is;
	}
}
