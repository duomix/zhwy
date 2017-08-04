package org.aisino.developer.util;

public class Tools {
	
	/**
	 * 首字母转大写
	 * @param s
	 * @return
	 */
	public static String FirstUpperCase(String s){
		if(null != s && !"".equals(s)){
			s = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
		}
		return s;
	}
	
	
	/**
	 * 转小写
	 * @param s
	 * @return
	 */
	public static String toLowerCase(String s){
		if(null != s && !"".equals(s)){
			s = s.toLowerCase();
		}
		return s;
	}
	
	/**
	 * 转大写
	 * @param s
	 * @return
	 */
	public static String toUpperCase(String s){
		if(null != s && !"".equals(s)){
			s = s.toUpperCase();
		}
		return s;
	}
	
	
	/**
	 * 截取第一个#号以前的部分   XX#AA# -> XX
	 * @param s
	 * @return
	 */
	public static String beformFirst(String s){
		if(s != null && s.lastIndexOf("#") > -1){
			s = s.substring(0, s.indexOf("#"));
		}
		return s;
	}
	
	
	/**
	 * 处理字符串 将s中下划线_去掉并将下滑线后第一个字母大写
	 * @param s
	 * @return
	 */
	public static String Format(String s){
		if(s != null && !"".equals(s)){
			int c = s.indexOf("_");
			if(c + 1 < s.length()){
				String t = s.substring(c+1, c+2);
				String u = t.toUpperCase();
				s = s.replace("_"+t, u);
			}
		}
		while(s.indexOf("_") > -1){
			s = Format(s);
		}
		return s;
	}
	
	
	
	/*public static void main(String[] args) {
		System.out.println(Format("aos"));
	}*/
}
