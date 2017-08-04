package org.aisino.zuul.common;

import org.aisino.utils.common.DateUtils;
import org.junit.Assert;
import org.junit.Test;

public class TimeUtilTest {

	//@Test
	public void testIsInTime() {
		 String   clientTime="20170427094523";
		 boolean res = TokenUtil.isInTime(clientTime);
		 Assert.assertTrue(res==false);
	}
	
	@Test
	public  void  testCreateToken() throws Exception{
		String  account = "node";
		String key = "ts123456";
		String   clientTime=DateUtils.getFormatDate("yyyyMMddHHmmss");
		//clientTime="20170703135127";
		String token = account+":"+clientTime+":"+HMACUtil.encode(account+clientTime,key);
		System.out.println(token);
		boolean  res = TokenUtil.checkToken(token, account, key);
		System.out.println(res);
	}

}
