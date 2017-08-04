package org.aisino.core.mybatis;


import java.util.HashMap;

public class XHashMap extends HashMap<Object,Object>{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 重写HashMap put 方法
	 * 如果 key 为String 则将 key转为小写
	 */
	@Override
	public Object put(Object key,Object value){
		if(key != null && key instanceof String){
			String tmp = (String) key;
			key = tmp.toLowerCase();
		}
		return super.put(key, value);
	}
}