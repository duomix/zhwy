package org.aisino.utils.common;


import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import net.iharder.Base64;

/**
 * DES加密 解密 工具
 */
public class DESUtils {
	private static byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128,
			-65 };
	
	/**
	 * DES加密
	 * @param data
	 * @return
	 */
	public static String encryptBasedDes(String data) {
		String encryptedData = null;
		try {
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY);

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);

			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(1, key, sr);
			encryptedData = new String(Base64.encodeBytes(cipher.doFinal(data.getBytes())));
		} catch (Exception e) {
			throw new RuntimeException("加密错误，错误信息：", e);
		}
		return encryptedData;
	}
	/**
	 * 重载  public static String encryptBasedDes(String data);
	 * @描述:
	 * @方法名: encryptBasedDes
	 * @param data
	 * @param password   密码，长度要是8的倍数
	 * @return
	 */
	public static String encryptBasedDes(String data,String password){
		if(password != null && password.length() >= 8){
			DES_KEY = password.getBytes();
			return encryptBasedDes(data);
		}else{
			return null;
		}
	}

	/**
	 * DES解密
	 * @param cryptData
	 * @return
	 */
	public static String decryptBasedDes(String cryptData) {
		String decryptedData = null;
		try {
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY);

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);

			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(2, key, sr);
			decryptedData = new String(cipher.doFinal(Base64.decode(cryptData)));
		} catch (Exception e) {
			throw new RuntimeException("解密错误，错误信息：", e);
		}
		return decryptedData;
	}
	
	/**
	 * 重载  public static String decryptBasedDes(String cryptData);
	 * @描述:
	 * @方法名: decryptBasedDes
	 * @param cryptData
	 * @param password   密码，长度要是8的倍数
	 * @return
	 */
	public static String decryptBasedDes(String cryptData,String password){
		if(password != null && password.length() >= 8){
			DES_KEY = password.getBytes();
			return decryptBasedDes(cryptData);
		}else{
			return null;
		}
	}
}
