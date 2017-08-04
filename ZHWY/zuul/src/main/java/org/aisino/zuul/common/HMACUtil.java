package org.aisino.zuul.common;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * HMAC信息摘要算法
 * @author hexing
 */
public class HMACUtil {
	  //HMAC加密算法名称
	   private static final String  HMAC="HmacSHA1";
	   //HMAC默认加密密钥
	   private static final String  KEYMAC="LTA1222B386001257";
	   //实例化Mac  
	   private  static  Mac mac;
	   
	   static{
		   try {
			   mac=Mac.getInstance(HMAC);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
	   }
	   
	   
	   /** 
	    * 用默认的秘钥计算HmacSHA1
	    * @param data 
	    * @return  String 返回消息摘要字符串
	    * */  
	   public static String encode(String data) throws Exception{  
	        return  encode(data, KEYMAC);
	   }  
	   
	   /**
	    * 用指定的秘钥计算HmacSHA1
	    * @param data
	    * @param key   秘钥
	    * @return
	    * @throws Exception
	    */
	   public static String encode(String data,String key) throws Exception{  
	       //还原密钥，因为密钥是以byte形式为消息传递算法所拥有  
	       SecretKey secretKey=new SecretKeySpec(key.getBytes("UTF-8"),HMAC);  
	       //初始化Mac  
	       mac.init(secretKey);  
	       //执行消息摘要处理  
	       //return new String(mac.doFinal(data.getBytes("UTF-8")),"UTF-8");
	       byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
	       StringBuilder sb=new StringBuilder();  
	       for(byte b:rawHmac){  
	    	   	sb.append(byteToHexString(b));  
	        }  
	        return sb.toString();  
	   }  
	   
	   private static String byteToHexString(byte ib){
		      char[] Digit={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
		      char[] ob=new char[2];  
		      ob[0]=Digit[(ib>>>4)& 0X0f];  
		      ob[1]=Digit[ib & 0X0F];  
		      String s=new String(ob);  
		      return s;           
	  }       
}
