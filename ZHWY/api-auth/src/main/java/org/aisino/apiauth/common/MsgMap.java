package org.aisino.apiauth.common;

import java.util.HashMap;
import java.util.Map;


/**
 * 结果集对象
 * @author root
 */
public class MsgMap {
	
	public  final  static  int  CODE_SUCCESS = 0;
	public  final  static  int  CODE_FAIL = 1;
	
	/**
	 * 格式化返回值
	 * @param code  调用编码  默认0:调用成功
	 * @param msg   调用说明
	 * @param data   请求数据结果集
	 * @return
	 */
	public static Map<String,Object> getMsg(int code,String msg,Object data){
		Map<String,Object> returnmap = new HashMap<String, Object>();
		returnmap.put("code", code);
		returnmap.put("msg", msg);
		returnmap.put("data", data);
		return returnmap;
	}
	
	/**
	 * 格式化成功返回值
	 * @param msg   调用说明
	 * @param data   请求数据结果集
	 * @return
	 */
	public static Map<String,Object> getSuccessMsg(String msg,Object data){
		Map<String,Object> returnmap = new HashMap<String, Object>();
		returnmap.put("code", CODE_SUCCESS);
		returnmap.put("msg", msg);
		returnmap.put("data", data);
		return returnmap;
	}
	
	
	/**
	 * 格式化成功返回值
	 * @param msg   调用说明
	 * @return
	 */
	public static Map<String,Object> getFailMsg(String msg){
		Map<String,Object> returnmap = new HashMap<String, Object>();
		returnmap.put("code", CODE_FAIL);
		returnmap.put("msg", msg);
		return returnmap;
	}
}
