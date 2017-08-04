package org.aisino.passport.sys.service.common;

import java.util.List;
import java.util.Map;

/**
 * service 工具
 * @author XZY
 * 2017-2-10-上午11:00:20
 */
public class ServiceUtils {
	
	/**
	 * 处理结果集中 key为checked 数据  为空赋值false 不为空赋值true
	 * @param d
	 * @return
	 */
	public static List<Map> dataListFormat(List<Map> d){
		if(d != null && d.size() > 0){
			for(int i=0;i<d.size();i++){
				Map tmp = d.get(i); 
				if(tmp.get("checked") != null && !"".equals(tmp.get("checked").toString())){
					tmp.put("checked", true);
				}else{
					tmp.put("checked", false);
				}
				Object o_leaf_node = tmp.get("leaf_node");
				if(o_leaf_node != null){
						tmp.put("isparent", Integer.valueOf(o_leaf_node.toString()) > 0 ? 1 : 0);
				}
			}
		}
		return d;
	}
	
	public static List<Map> listToTree(List<Map> d){
		if(d != null && d.size() > 0){
			for(int i=0;i<d.size();i++){
				Map tmp = d.get(i); 
				if(tmp.get("checked") != null && "1".equals(tmp.get("checked").toString())){
					tmp.put("checked", true);
				}else{
					tmp.put("checked", false);
				}
				Object o_leaf_node = tmp.get("isparent");
				tmp.put("leaf_node", Integer.valueOf(o_leaf_node.toString()));
			}
		}
		return d;
	}
}
