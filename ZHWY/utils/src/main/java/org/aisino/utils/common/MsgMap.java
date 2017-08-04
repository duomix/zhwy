package org.aisino.utils.common;

import java.util.HashMap;
import java.util.Map;


/**
 * 结果集对象
 * @author root
 *
 */
public class MsgMap {
	
	public static Map<String,Object> returnmap = new HashMap<String, Object>();
	
	
	/**
	 * 
	 * @param code  调用编码  默认0:调用成功
	 * @param msg   调用说明
	 * @param data   请求数据结果集
	 * @return
	 */
	public static Map<String,Object> getMsg(Integer code,String msg,Object data){
		if(code == null || "".equals(code)){
			code = 0;
		}
		returnmap.put("code", code);
		returnmap.put("msg", msg);
		returnmap.put("data", data);
		return returnmap;
	}
}
