package org.aisino.developer.util;



/**
 * 获得Dao 文件内容
 * @author Administrator
 *
 */
public class XFileDaoContent {
	
	/**
	 * 获取接口内容
	 * @param path
	 * @param tablename
	 * @param i
	 * @return
	 */
	public static String getIContent(String path,String tablename,int i){
		if(path.indexOf("#") > -1){
			String ts[] = path.split("#");
			path = ts[1];
		}
		tablename = Tools.FirstUpperCase(tablename);
		StringBuffer bf = new StringBuffer();
		if(1 == i){//I
			//定义包
			bf.append("package " + path + ".dao;");
			bf.append("\n");
			bf.append("\n");
			//导入支持包
			bf.append("import org.aisino.core.base.BaseDao;");
			bf.append("\n");
			tablename = Tools.Format(tablename);
			bf.append("import " + path + ".vo." + tablename + ";");
			bf.append("\n");
			bf.append("\n");
//			bf.append("@MyBatisDao");
//			bf.append("\n");
			bf.append("public interface I" + tablename + "Dao extends BaseDao<"+tablename+">{");
			bf.append("\n");
			bf.append("\n");
				/*//分页查询和总条数
				bf.append("	public List query" + tablename + "ListPage(Map map);");
				bf.append("\n");
				bf.append("	public Integer query" + tablename + "ListCount(Map map);");
				bf.append("\n");
				bf.append("\n");
				//查询所有满足条件的List集合
				bf.append("	public List query" + tablename + "List(Map map);");
				bf.append("\n");
				bf.append("\n");
				//查询一条信息详情
				bf.append("	public Map query" + tablename + "Info(Map map);");
				bf.append("\n");
				bf.append("\n");
				//添加
				bf.append("	public Integer add" + tablename + "Info(Map map);");
				bf.append("\n");
				bf.append("\n");
				//修改
				bf.append("	public Integer update" + tablename + "Info(Map map);");
				bf.append("\n");
				bf.append("\n");
				//删除
				bf.append("	public Integer delete" + tablename + "Info(Map map);");
				bf.append("\n");*/
			bf.append("}");
			
		}else if(2 == i){//impl
			/*//定义包
			bf.append("package " + path + ".dao.impl;");
			bf.append("\n");
			bf.append("\n");
			//导入支持包
			bf.append("import java.util.List;");
			bf.append("\n");
			bf.append("import java.util.Map;");
			bf.append("\n");
			bf.append("\n");
			bf.append("import org.springframework.stereotype.Service;");
			bf.append("\n");
			bf.append("import " + path + ".vo." + tablename + ";");
			bf.append("\n");
			bf.append("import " + path + ".dao.I" + tablename + "Dao;");
			bf.append("\n");
			bf.append("\n");
			
			bf.append("@Service");
			bf.append("\n");
			bf.append("public class " + tablename + "DaoImpl extends BaseDao implements I" + tablename + "Dao{");
			bf.append("\n");
			bf.append("\n");
			
			//分页查询和总条数
			bf.append("	public List query" + tablename + "ListPage("+tablename+" "+tablename.toLowerCase()+"){");
			bf.append("\n");
				bf.append("		List list =  (List) getSqlMapClientTemplate().queryForList(\"query" + tablename + "ListPage\", "+tablename.toLowerCase()+");");
				bf.append("\n");
				bf.append("		return list;");
				bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			bf.append("	public Integer query" + tablename + "ListCount("+tablename+" "+tablename.toLowerCase()+"){");
			bf.append("\n");
				bf.append("		return (Integer) getSqlMapClientTemplate().queryForObject(\"query" + tablename + "ListCount\", "+tablename.toLowerCase()+");");
				bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			bf.append("\n");
			
			//查询所有满足条件的List集合
			bf.append("	public List query" + tablename + "List("+tablename+" "+tablename.toLowerCase()+"){");
			bf.append("\n");
				bf.append("		List list =  (List) getSqlMapClientTemplate().queryForList(\"query" + tablename + "List\", "+tablename.toLowerCase()+");");
				bf.append("\n");
				bf.append("		return list;");
				bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			bf.append("\n");
			
			//查询一条信息详情
			bf.append("	public Map query" + tablename + "Info("+tablename+" "+tablename.toLowerCase()+"){");
			bf.append("\n");
				bf.append("		Map x = (Map) getSqlMapClientTemplate().queryForObject(\"query" + tablename + "Info\", "+tablename.toLowerCase()+");");
				bf.append("\n");
				bf.append("		return x;");
				bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			bf.append("\n");
			
			//添加
			bf.append("	public Integer add" + tablename + "Info("+tablename+" "+tablename.toLowerCase()+"){");
			bf.append("\n");
				bf.append("		getSqlMapClientTemplate().insert(\"add"+tablename+"\", "+tablename.toLowerCase()+");");
				bf.append("\n");
				bf.append("		return 1;");
				bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			bf.append("\n");
			
			//修改
			bf.append("	public Integer update" + tablename + "Info("+tablename+" "+tablename.toLowerCase()+"){");
			bf.append("\n");
				bf.append("		return getSqlMapClientTemplate().update(\"update"+tablename+"\", "+tablename.toLowerCase()+");");
				bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			bf.append("\n");
			
			//删除
			bf.append("	public Integer delete" + tablename + "Info("+tablename+" "+tablename.toLowerCase()+"){");
				bf.append("\n");
				bf.append("		return getSqlMapClientTemplate().delete(\"del"+tablename+"\", "+tablename.toLowerCase()+");");
				bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			
			bf.append("}");*/
		}
		return bf.toString();
	}
}
