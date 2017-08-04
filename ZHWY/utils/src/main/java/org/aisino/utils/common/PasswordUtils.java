package org.aisino.utils.common;

import org.apache.shiro.crypto.hash.Md5Hash;




/**
 * 密码 工具
 */
public class PasswordUtils {
	
	/**
	 * 加密 KEY
	 */
	private static final String password = "zhwy2017-2020";
	
	/**
	 * DES密码编码/加密
	 * @描述:
	 * @方法名: passEncrypt
	 * @param data
	 * @return
	 * @返回类型 String
	 */
	public static String passEncrypt(String data){
		return DESUtils.encryptBasedDes(data,password);
	}
	
	/**
	 * DES密码编码/解密
	 * @描述:
	 * @方法名: passBasedDes
	 * @param data
	 * @return
	 * @返回类型 String
	 */
	public static String passBasedDes(String data){
		return DESUtils.decryptBasedDes(data,password);
	}
	
	/**
	 * 验证 密码是否正确
	 * @描述:
	 * @方法名: Validator
	 * @param pa  前台传入明文
	 * @param BDPa  数据库密文
	 * @return
	 * @返回类型 boolean  true：密码验证正确   fasle：密码验证失败
	 */
	public static boolean Validator(String pa,String BDPa){
		boolean b = false;
		if(pa != null && !"".equals(pa) && BDPa != null && !"".equals(BDPa)){
			if(pa.equals(BDPa)){
				b = true;
			}
		}
		return b;
	}
	
	/**
	 * MD5密码加密算法
	 * @param ac   账户
	 * @param pa  密码
	 * @return  加密后秘文
	 */
	public static String MD5Encrypt(String ac, String pa){
		pa = new Md5Hash(pa, ac).toHex();
		return pa;
	}
	
}
