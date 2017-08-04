package org.aisino.zuul.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class TokenUtil {
	
	private  final static  Logger log = LoggerFactory.getLogger(TokenUtil.class);
	
	/**
	 * 客户端token时间相对于服务器的误差范围，默认前后不超过5分钟。
	 */
	private   final  static   long  defaultTokenTime= 300000l;
	
	private   final  static  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public   static   boolean   isInTime(String sourceTime){
			if(StringUtils.isEmpty(sourceTime))   return false;
			long  clientTime = 0l;
			try {
				 clientTime = sdf.parse(sourceTime).getTime();
			} catch (ParseException e) {
				return false;
			}
			
			long  serverTime = System.currentTimeMillis();
			if(clientTime>(serverTime-defaultTokenTime) && clientTime<(serverTime+defaultTokenTime)){
				return true;
			}else{
				return false;
			}
	}
	
	/*
	 * 验证token，token形如    aisino:20170426100956:qw2e3i9ie3e8u8u8
	 * strs[0]为接口调用用户
	 * strs[1]为客户端接口调用时间
	 * strs[2]为客户端计算的HMAC摘要字符串
	 * 验证规则
	 * 首先验证用户是否合法
	 * 再验证时间是否在允许的范围内，暂设为5分钟
	 * 最后验证HMAC摘要是否正确
	 */
	public   static  boolean   checkToken(String token,String account,String key){
		if(!StringUtils.isEmpty(token)){
				String[]  strs = token.split(":");
				if(strs.length==3){
					if(account.equals(strs[0])){
						if(TokenUtil.isInTime(strs[1])){
								String   hmac = "";
								try {
									hmac = HMACUtil.encode(strs[0]+strs[1],key);
									log.debug("account:{}  time:{}  hmac: {}",strs[0],strs[1],strs[2]);
									log.debug("摘要比较，客户端：{}   服务器端：{}",strs[2],hmac);
									if(hmac.equals(strs[2])){
										return true;
									}else{
										log.warn("不合法的用户，客户端account：{}  token:{}",account,strs[2]);
									}
								} catch (Exception e) {
									log.warn("摘要计算异常",e);
								}
						}else{
							log.warn("token:{} 已超时",token);
						}
					}
				}
		}
		return false;
	}
}
