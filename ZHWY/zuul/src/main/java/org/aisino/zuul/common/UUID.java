package org.aisino.zuul.common;


/**
 * UUID 
 */
public class UUID {
	 
	/**
	 * 生成 32 位UUID
	 * @return
	 */
	public static String GetUUID32(){
		String str = java.util.UUID.randomUUID().toString();
		String uuids = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);   
		return uuids;
	}
}
