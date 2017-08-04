package org.aisino.zuul.common;


/**
 * 系统常量
 */
public class XConstants {
	
	static PropertiesUtils applicationprop = PropertiesUtils.getInstance("application.properties");
	
	/**
	 * #token验证开关  true:开启验证  false:关闭验证
	 */
	public static final String TOKEN_SWITCH = applicationprop.getProperty("token.switch");
	
	/**
	 * #app用户单设备登陆开关  true:只能一个设备登陆  false:可多设备登陆
	 */
	public static final String APPUSERLOGIN_SWITCH = applicationprop.getProperty("appuserlogin.switch");
		
}

