package org.aisino.developer.util;



/**
 * 获得Service 文件内容
 * @author Administrator
 *
 */
public class XFileServiceContent {
	
	/**
	 * 获取接口内容
	 * @param path
	 * @param tablename
	 * @param i
	 * @return
	 */
	public static String getIContent(String path,String tablename,int i,String databasename){
		if(path.indexOf("#") > -1){
			String ts[] = path.split("#");
			path = ts[1];
		}
		tablename = Tools.FirstUpperCase(tablename);
		tablename = Tools.Format(tablename);
		StringBuffer bf = new StringBuffer();
		if(1 == i){//I
			//定义包
			bf.append("package " + path + ".service;");
			bf.append("\n");
			bf.append("\n");
			//导入支持包
			bf.append("import org.aisino.core.base.IBaseService;");
			bf.append("\n");
			bf.append("import " + path + ".vo." + tablename + ";");
			bf.append("\n");
			bf.append("\n");
			
			bf.append("public interface I" + tablename + "Service extends IBaseService<" + tablename + ">{");
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
			//定义包
			bf.append("package " + path + ".service.impl;");
			bf.append("\n");
			bf.append("\n");
			//导入支持包
			bf.append("import org.springframework.stereotype.Service;");
			bf.append("\n");
			bf.append("import org.aisino.core.base.BaseService;");
			bf.append("\n");
			bf.append("import " + path + ".service.I" + tablename + "Service;");
			bf.append("\n");
			bf.append("import " + path + ".vo." + tablename + ";");
			bf.append("\n");
			bf.append("import " + path + ".dao.I" + tablename + "Dao;");
			bf.append("\n");
			bf.append("\n");
			
			bf.append("@Service");
			bf.append("\n");
			bf.append("public class " + tablename + "ServiceImpl  extends BaseService<"+tablename+",I"+tablename+"Dao> implements I" + tablename + "Service{");
			bf.append("\n");
			bf.append("\n");
			
			/*bf.append("		@Autowired");
			bf.append("\n");
			bf.append("		private I"+tablename+"Dao xdao;");
			bf.append("\n");*/
			bf.append("\n");
			
			/*//分页查询和总条数
			bf.append("	public List query" + tablename + "ListPage(Map map){");
			bf.append("\n");
				bf.append("		List list =  xdao.query" + tablename + "ListPage(map);");
				bf.append("\n");
				bf.append("		return list;");
				bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			bf.append("	public Integer query" + tablename + "ListCount(Map map){");
			bf.append("\n");
				bf.append("		return xdao.query" + tablename + "ListCount(map);");
				bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			bf.append("\n");
			
			//查询所有满足条件的List集合
			bf.append("	public List query" + tablename + "List(Map map){");
			bf.append("\n");
				bf.append("		List list =  xdao.query" + tablename + "List(map);");
				bf.append("\n");
				bf.append("		return list;");
				bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			bf.append("\n");
			
			//查询一条信息详情
			bf.append("	public Map query" + tablename + "Info(Map map){");
			bf.append("\n");
				bf.append("		Map x = xdao.query" + tablename + "Info(map);");
				bf.append("\n");
				bf.append("		return x;");
				bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			bf.append("\n");
			
			//获得表主键字段
			String xidname = DB.getXidName(tablename,databasename);
			xidname = xidname.substring(0, 1).toUpperCase()+ xidname.substring(1).toLowerCase();
			//添加
			bf.append("	public Integer add" + tablename + "Info(Map map){");
			bf.append("\n");
				bf.append("		map.put(\""+xidname.toLowerCase()+"\",UUID.GetUUID32());");
				bf.append("\n");
				bf.append("		Integer c = xdao.add" + tablename + "Info(map);");
				bf.append("\n");
				bf.append("		return c;");
				bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			bf.append("\n");
			
			//修改
			bf.append("	public Integer update" + tablename + "Info(Map map){");
			bf.append("\n");
				bf.append("		String id = map.get(\""+xidname+"\").toString();");
				bf.append("\n");
				bf.append("		if(id != null && !\"\".equals(id)){");
				bf.append("\n");
					bf.append("			return xdao.update" + tablename + "Info(map);");
					bf.append("\n");
				bf.append("		}else{");
				bf.append("\n");
				bf.append("			return -1;");
				bf.append("\n");
				bf.append("		}");
				bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			bf.append("\n");
			
			//删除
			bf.append("	@Transactional");
			bf.append("\n");
			bf.append("	public Integer delete" + tablename + "Info(Map map){");
			bf.append("\n");
				bf.append("		String[] ids = (String[])map.get(\""+xidname.toLowerCase()+"\");");
				bf.append("\n");
				bf.append("		if(ids != null && ids.length > 0){");
				bf.append("\n");
					bf.append("				for(int i=0;i<ids.length;i++){");
					bf.append("\n");
					bf.append("					map.put(\""+xidname.toLowerCase()+"\",ids[i]);");
					bf.append("\n");
					bf.append("					 xdao.delete" + tablename + "Info(map);");
					bf.append("\n");
					bf.append("				}");
					bf.append("\n");
					bf.append("				return 1;");
					bf.append("\n");
				bf.append("		}else{");
				bf.append("\n");
				bf.append("			return -1;");
				bf.append("\n");
				bf.append("		}");
				bf.append("\n");	
			bf.append("	}");
			bf.append("\n");*/
			
			bf.append("}");
		}
		return bf.toString();
	}
}
