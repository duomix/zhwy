package org.aisino.utils.common;

import java.util.Collection;
import java.util.Map;


/**
 * 验证 对象  非空 工具类
 * @author XZY
 *
 * 2015-12-23下午2:04:28
 */
public class EmptyUtils {
	
	
	/**
     * 判断Collection(List和Set) 是否为空
     *
     * @param collection
     *            List或Set类型的集合
     * @return 
     * 		如果collection是 null或size=0，返回true；否则，返回false。
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * 判断map是否为空
     *
     * @param map
     *            键值对数据类型
     * @return 
     * 		如果map是 null或size=0，返回true；否则，返回false。
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.size() == 0;
    }

    /**
     * 判断一个数组是否为空。
     *
     * @param array
     *            对象数组
     * @return 
     * 		如果数组为null或者数组元素个数为0，返回true；否则，返回false。
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }
    
    
    /**
     * 判断一个字符串 是否为空
     * 
     * @param str
     * 			字符串
     * @return 
     * 		如果字符串为null 或者 "" ，返回true； 否则返回false。
     */
    public static boolean isEmpty(String str){
    	return str == null || "".equals(str);
    }
    
    /**
     * 判断一个对象 是否为 null
     * @param obj
     * 			Object 类型
     * @return 
     * 		如果 obj == null 返回 true , 否则 返回 false
     */
    public static boolean isEmpty(Object obj){
    	return obj == null;
    }

    /**
     * 判断Collection(List和Set) 不为空
     *
     * @param collection
     *            List或Set类型的集合
     * @return 
     * 		如果collection不等于null且size>0，返回true；否则，返回false。
     */
    public static boolean notEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断map不为空
     *
     * @param map
     *            键值对数据类型
     * @return 
     * 		如果map不为 null且size>0，返回true；否则，返回false。
     */
    public static boolean notEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 判断一个数组不为空。
     *
     * @param array
     *            对象数组
     * @return 
     * 		如果数组为null或者数组元素个数为0，返回false；否则，返回true。
     */
    public static boolean notEmpty(Object[] array) {
        return !isEmpty(array);
    }
    
    /**
     * 判断一个字符串 不为空
     * 
     * @param str
     * 			字符串
     * @return 
     * 		如果字符串为null 或者 "" ，返回false； 否则返回true。
     */
    public static boolean notEmpty(String str){
    	return !isEmpty(str);
    }
    
    /**
	 * 判断对象是否为NotEmpty(!null或元素>0)<br>
	 * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
	 * 
	 * @param pObj
	 *            待检查对象
	 * @return boolean 返回的布尔值
	 */
	public static boolean isNotEmpty(Object pObj) {
		if (pObj == null)
			return false;
		if (pObj == "")
			return false;
		if (pObj instanceof String) {
			if (((String) pObj).length() == 0) {
				return false;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection) pObj).size() == 0) {
				return false;
			}
		} else if (pObj instanceof Map) {
			if (((Map) pObj).size() == 0) {
				return false;
			}
		}
		return true;
	}

}
