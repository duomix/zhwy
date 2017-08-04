package org.aisino.developer.util;

import org.aisino.developer.DB;




/**
 * 获得Controller 文件内容
 * @author Administrator
 *
 */
public class XFileControllerContent {
	
	/**
	 * 
	 * @param path
	 * @param tablename
	 * @return
	 */
	public static String getIContent(String path,String tablename,String databasename){
		if(path.indexOf("#") > -1){
			String ts[] = path.split("#");
			path = ts[1];
		}
		//表主键字符串
		String xid = DB.getXidName(tablename, databasename);
		//转换首字母大写
		xid = xid.substring(0, 1).toUpperCase()+xid.substring(1, xid.length());
		tablename = Tools.FirstUpperCase(tablename);
		StringBuffer bf = new StringBuffer();
			//定义包
			bf.append("package " + path + ".controller;");
			bf.append("\n");
			bf.append("\n");
			//导入支持包
			bf.append("import java.util.Map;");
			bf.append("\n");
			bf.append("import org.aisino.utils.common.MsgMap;");
			bf.append("\n");
			bf.append("import org.aisino.utils.common.UUID;");
			bf.append("\n");
			bf.append("import " + path + ".common.XConstants;");
			bf.append("\n");
			bf.append("import org.springframework.beans.factory.annotation.Autowired;");
			bf.append("\n");
			bf.append("import org.springframework.web.bind.annotation.RequestMapping;");
			bf.append("\n");
			bf.append("import org.springframework.web.bind.annotation.RestController;");
			bf.append("\n");
			bf.append("import com.github.pagehelper.PageInfo;");
			bf.append("\n");
//			bf.append("import org.springframework.web.bind.annotation.ResponseBody;");
//			bf.append("\n");
			tablename = Tools.Format(tablename);
			bf.append("import " + path+ ".service.I" + tablename + "Service;");
			bf.append("\n");
			bf.append("import " + path+ ".vo." + tablename + ";");
			bf.append("\n");
			bf.append("\n");
			
			
			bf.append("@RestController");
			bf.append("\n");
			bf.append("@RequestMapping({\"/"+tablename.toLowerCase()+"\"})");
			bf.append("\n");
			bf.append("public class " + tablename + "Controller {");
			bf.append("\n");
			bf.append("\n");
				bf.append("	@Autowired");
				bf.append("\n");
				bf.append("	private I" + tablename + "Service xservice;");
				bf.append("\n");
				bf.append("\n");
			
			String tmp = null;
			//查询列表
			bf.append("	@RequestMapping({\"/findlist.do\"})");
			bf.append("\n");	
//			bf.append("	@ResponseBody");
//			bf.append("\n");	
			tmp = "public Map<String,Object> findList("+tablename+" bean){";
			bf.append("	"+tmp);
			bf.append("\n");	
			tmp = "PageInfo<"+tablename+"> data = xservice.findPage(bean);";
			bf.append("		"+tmp);
			bf.append("\n");	
			bf.append("		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, data);");
			bf.append("\n");	
			bf.append("		return mm;");
			bf.append("\n");	
			bf.append("	}");
			bf.append("\n");	
			bf.append("\n");
			
			//查询单个信息
			bf.append("	@RequestMapping({\"/get.do\"})");
			bf.append("\n");	
//			bf.append("	@ResponseBody");
//			bf.append("\n");
			tmp = "public Map<String,Object> get("+tablename+" bean){";
			bf.append("	"+tmp);
			bf.append("\n");
			bf.append("		bean = xservice.get(bean);");
			bf.append("\n");
			bf.append("		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, bean);");
			bf.append("\n");
			bf.append("		return mm;");
			bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			bf.append("\n");
			
			//新增信息
			bf.append("	@RequestMapping({\"/add.do\"})");
			bf.append("\n");	
//			bf.append("	@ResponseBody");
//			bf.append("\n");
			tmp = "public Map<String,Object> add("+tablename+" bean){";
			bf.append("	"+tmp);
			bf.append("\n");
			bf.append("		Map<String,Object> mm = null;");
			bf.append("\n");
			tmp = "bean.set"+xid+"(UUID.GetUUID32());//设置主键";
			bf.append("		"+tmp);
			bf.append("\n");
			bf.append("		Integer c = xservice.insert(bean);");
			bf.append("\n");
			bf.append("		if(c > 0){");
			bf.append("\n");
			bf.append("			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);");
			bf.append("\n");
			bf.append("		}else{");
			bf.append("\n");
			bf.append("				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);");
			bf.append("\n");
			bf.append("		}");
			bf.append("\n");
			bf.append("		return mm;");
			bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			bf.append("\n");
			
			//更新信息
			bf.append("	@RequestMapping({\"/update.do\"})");
			bf.append("\n");	
//			bf.append("	@ResponseBody");
//			bf.append("\n");
			tmp = "public Map<String,Object> update("+tablename+" bean){";
			bf.append("	"+tmp);
			bf.append("\n");
			bf.append("		Map<String,Object> mm = null;");
			bf.append("\n");
			bf.append("		Integer c = xservice.update(bean);");
			bf.append("\n");
			bf.append("		if(c > 0){");
			bf.append("\n");
			bf.append("			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);");
			bf.append("\n");
			bf.append("		}else{");
			bf.append("\n");
			bf.append("				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);");
			bf.append("\n");
			bf.append("		}");
			bf.append("\n");
			bf.append("		return mm;");
			bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			
			
			//删除信息(支持批量主键以,号分割)
			bf.append("	@RequestMapping({\"/delete.do\"})");
			bf.append("\n");	
//			bf.append("	@ResponseBody");
//			bf.append("\n");
			tmp = "public Map<String,Object> delete("+tablename+" bean){";
			bf.append("	"+tmp);
			bf.append("\n");
			bf.append("		Map<String,Object> mm = null;");
			bf.append("\n");
			bf.append("		Integer c  = 0;");
			bf.append("\n");
			bf.append("		String[] ids = null;");
			bf.append("\n");
			tmp = "String id = bean.get"+xid+"();//主键";
			bf.append("		"+tmp);
			bf.append("\n");
			bf.append("		if(id != null && !\"\".equals(id)){");
			bf.append("\n");
			bf.append("			if(id.indexOf(\",\") > -1){");
			bf.append("\n");
			bf.append("				ids = id.split(\",\");");
			bf.append("\n");
			bf.append("			}else{");
			bf.append("\n");
			bf.append("				ids = new String[1];");
			bf.append("\n");
			bf.append("				ids[0] = id;");
			bf.append("\n");
			bf.append("			}");
			bf.append("\n");
			bf.append("			for(int i=0;i<ids.length;i++){");
			bf.append("\n");
			bf.append("				c += xservice.delete(ids[i]);");
			bf.append("\n");
			bf.append("			}");
			bf.append("\n");
			bf.append("			if(c > 0){");
			bf.append("\n");
			bf.append("				mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);");
			bf.append("\n");
			bf.append("			}else{");
			bf.append("\n");
			bf.append("				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);");
			bf.append("\n");
			bf.append("			}");
			bf.append("\n");
			bf.append("		}else{");
			bf.append("\n");
			bf.append("			mm = MsgMap.getMsg(XConstants.PARAM_ERROR_CODE,XConstants.PARAM_ERROR, null);");
			bf.append("\n");
			bf.append("		}");
			bf.append("\n");
			bf.append("		return mm;");
			bf.append("\n");
			bf.append("	}");
			bf.append("\n");
			
			
			bf.append("\n");
			bf.append("}");
		return bf.toString();
	}
}
