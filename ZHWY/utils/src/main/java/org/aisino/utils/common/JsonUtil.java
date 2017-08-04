package org.aisino.utils.common;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

/**
 * JSON 工具类
 * 
 * @author XZY
 * 
 *         2015-12-25下午3:38:28
 */
public class JsonUtil {

	/**
	 * 将 Object 转换为 JSon 字符串
	 * 
	 * @param obj
	 *            需要转换的 Object 对象
	 * @return 返回 转换后的 json 字符串
	 */
	public static String object2json(Object obj) {
		StringBuilder json = new StringBuilder();
		if (obj == null) {
			json.append("\"\"");
		} else if (obj instanceof String || obj instanceof Integer
				|| obj instanceof Float || obj instanceof Boolean
				|| obj instanceof Short || obj instanceof Double
				|| obj instanceof Long || obj instanceof BigDecimal
				|| obj instanceof BigInteger || obj instanceof Byte) {
			json.append("\"").append(string2json(obj.toString())).append("\"");
		} else if (obj instanceof Object[]) {
			json.append(array2json((Object[]) obj));
		} else if (obj instanceof List) {
			json.append(list2json((List<?>) obj));
		} else if (obj instanceof Map) {
			json.append(map2json((Map<?, ?>) obj));
		} else if (obj instanceof Set) {
			json.append(set2json((Set<?>) obj));
		} else {
			json.append(bean2json(obj));
		}
		return json.toString();
	}

	/**
	 * 将 javabean 转换为 JSon 字符串
	 * 
	 * @param bean
	 *            需要转换的 bean 对象
	 * @return 返回 转换后的 json 字符串
	 */
	public static String bean2json(Object bean) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class)
					.getPropertyDescriptors();
		} catch (IntrospectionException e) {
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					String name = object2json(props[i].getName());
					String value = object2json(props[i].getReadMethod().invoke(
							bean));
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
				} catch (Exception e) {
				}
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	/**
	 * 将 List<?> 转换为 JSon 字符串
	 * 
	 * @param list
	 *            需要转换的 list 对象
	 * @return 返回 转换后的 json 字符串
	 */
	public static String list2json(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * 将 Object[] 转换为 JSon 字符串
	 * 
	 * @param array
	 *            需要转换的 array 对象
	 * @return 返回 转换后的 json 字符串
	 */
	public static String array2json(Object[] array) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (array != null && array.length > 0) {
			for (Object obj : array) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * 将 Map<?, ?> 转换为 JSon 字符串
	 * 
	 * @param map
	 *            需要转换的 map 对象
	 * @return 返回 转换后的 json 字符串
	 */
	public static String map2json(Map<?, ?> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				json.append(object2json(key));
				json.append(":");
				json.append(object2json(map.get(key)));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	/**
	 * 将 Set<?> 转换为 JSon 字符串
	 * 
	 * @param set
	 *            需要转换的 set 对象
	 * @return 返回 转换后的 json 字符串
	 */
	public static String set2json(Set<?> set) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (set != null && set.size() > 0) {
			for (Object obj : set) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * 暂无用处
	 * 
	 * @param s
	 * @return
	 */
	public static String string2json(String s) {
		if (s == null)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		}
		return sb.toString();
	}

	
	
	/**
	 * json 字符串  转换成map对象 
	 * @描述:
	 * @方法名: JsonObjStr2Map
	 * @param jsonObjStr  例如:{'name':'get','int':1,'double',1.1,'null':null}
	 * @return
	 * @返回类型 Map<String,Object>
	 * @创建人 XZY
	 * @创建时间 2016-2-3下午2:19:02
	 * @修改人 XZY
	 * @修改时间 2016-2-3下午2:19:02
	 * @修改备注
	 * @since
	 * @throws
	 */
	public static Map<String,Object> JsonObjStr2Map(String jsonObjStr) {
		JSONObject jsonObject = JSONObject.fromObject(jsonObjStr);
		Map<String,Object> map = new HashMap<String,Object>();
		for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			map.put(key, jsonObject.get(key));
		}
		return map;
	}
	
	
	/**
	 * json 字符串  转换成JavaBean对象 
	 * @描述:
	 * @方法名: JsonObjStr2JavaBean
	 * @param jsonObjStr
	 * @param bean
	 * @return
	 * @返回类型 Object
	 * @创建人 XZY
	 * @创建时间 2016-2-3下午2:49:21
	 * @修改人 XZY
	 * @修改时间 2016-2-3下午2:49:21
	 * @修改备注
	 * @since
	 * @throws
	 */
	public static <T> T JsonObjStr2JavaBean(String jsonString, Class<T> beanCalss) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        T bean = (T) JSONObject.toBean(jsonObject, beanCalss);
        return bean;
         
    }
 
	
	
	/**
	 * json 字符串  转换成List对象 
	 * @描述:
	 * @方法名: JsonObjStr2List
	 * @param jsonObjStr
	 * @return
	 * @返回类型 Object
	 * @创建人 XZY
	 * @创建时间 2016-2-3下午2:50:46
	 * @修改人 XZY
	 * @修改时间 2016-2-3下午2:50:46
	 * @修改备注
	 * @since
	 * @throws
	 */
	public static Object JsonObjStr2List(String jsonObjStr) {
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(jsonObjStr);
		List<Map<String,Object>> output = new ArrayList<Map<String,Object>>();
		for(int i=0;i<jsonArray.size();i++){
			output.add((Map<String, Object>) jsonArray.get(i));
		}
		return output;
	}
	
	
	/**
	 * json 字符串  转换成Array对象 
	 * @描述:
	 * @方法名: JsonObjStr2Array
	 * @param jsonObjStr
	 * @return
	 * @返回类型 Object
	 * @创建人 XZY
	 * @创建时间 2016-2-3下午2:53:12
	 * @修改人 XZY
	 * @修改时间 2016-2-3下午2:53:12
	 * @修改备注
	 * @since
	 * @throws
	 */
	public static Object JsonObjStr2Array(String jsonObjStr) {
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(jsonObjStr);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
		Object[] output = (Object[]) JSONSerializer.toJava(jsonArray, jsonConfig);
		return output;
	}

	
	public static void main(String[] args) {
		Map m = new HashMap();
		Map m2 = new HashMap();
		List list = new ArrayList();
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(m2);
		m2.put("2", 2);
		m.put("key", 1);
		m.put("map", m2);
		m.put("list", list);
		String json = map2json(m);
		System.out.println(json);
		System.out.println(JsonObjStr2Map(json).get("key"));
	}
}
