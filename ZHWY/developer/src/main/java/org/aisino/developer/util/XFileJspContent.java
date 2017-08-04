package org.aisino.developer.util;

import java.util.List;
import java.util.Map;

import org.aisino.developer.DB;




/**
 * 获得Jsp 文件内容
 * @author Administrator
 *
 */
public class XFileJspContent {
	
	/**
	 * 获取jsp内容
	 * @param path
	 * @param tablename
	 * @return
	 */
	public static String getJspContent(String path,String tablename,String databasename){
		if(path.indexOf("#") > -1){
			String ts[] = path.split("#");
			path = ts[1];
		}
		String xid = DB.getXidName(tablename,databasename);
		//查询表所有字段 并判断是否生成add form字段
		List tcolus = DB.getTableNameColus(tablename,databasename);
		tablename = Tools.Format(tablename);
		tablename = Tools.toLowerCase(tablename);
		StringBuffer bf = new StringBuffer();
			bf.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
			bf.append("\n");
			bf.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:th=\"http://www.thymeleaf.org\">");
			bf.append("\n");
			bf.append("	<head>");
			bf.append("\n");
			bf.append("		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			bf.append("\n");
			bf.append("		<title></title>");
			bf.append("\n");
			bf.append("		<style type=\"text/css\">");
			bf.append("\n");
			bf.append("			.xtext {");
			bf.append("\n");
			bf.append("				color: #122e29;");
			bf.append("\n");
			bf.append("				border: 1px solid #A4BED4;");
			bf.append("\n");
			bf.append("				height: 25px;");
			bf.append("\n");
			bf.append("				width: 235px;");
			bf.append("\n");
			bf.append("				margin-bottom: 10px;");
			bf.append("\n");
			bf.append("				margin-left: 5px;");
			bf.append("\n");
			bf.append("			}");
			bf.append("\n");
			bf.append("		</style>");
			bf.append("\n");
			
			bf.append("<link rel=\"stylesheet\" type=\"text/css\" th:href=\"@{easyui/themes/bootstrap/easyui.css}\" />");
			bf.append("\n");
			bf.append("<link rel=\"stylesheet\" type=\"text/css\" th:href=\"@{easyui/themes/icon.css}\" />");
			bf.append("\n");
			bf.append("<link rel=\"stylesheet\" type=\"text/css\" th:href=\"@{css/easyui_demo.css}\" />");
			bf.append("\n");
			bf.append("<script type=\"text/javascript\" th:src=\"@{easyui/jquery.min.js}\"></script>");
			bf.append("\n");
			bf.append("<script type=\"text/javascript\" th:src=\"@{easyui/jquery.easyui.min.js}\"></script>");
			bf.append("\n");
			bf.append("<script type=\"text/javascript\" th:src=\"@{easyui/jquery.easyui.min.js}\"></script>");
			bf.append("\n");
			bf.append("<script type=\"text/javascript\" th:src=\"@{easyui/locale/easyui-lang-zh_CN.js}\"></script>");
			bf.append("\n");
			
			bf.append("<script type=\"text/javascript\" th:src=\"@{js/" + tablename + ".js}\"></script>");
			bf.append("\n");
			bf.append("	</head>");
			bf.append("\n");
			
			
			
			
			bf.append("	<body class=\"easyui-layout\">");
			bf.append("\n");
			bf.append("		<div data-options=\"region:'north',border:false\" style=\"height:60px;padding:10px\">查询区域</div>");
			bf.append("\n");
			bf.append("			<div data-options=\"region:'center',title:\''\" >");
			bf.append("\n");
			bf.append("				<div>");
			bf.append("\n");
			bf.append("					<table id=\"list_" + tablename + "_table\"></table>");
			bf.append("\n");
			bf.append("				</div>");
			bf.append("\n");
			bf.append("			</div>");
			bf.append("\n");
			
			//<!-- 新增 or  div -->
			bf.append("				<div id='addorupdatewin' style=\"overflow-y:scroll;\">");
			bf.append("\n");
			bf.append("				<div style=\"margin: 20px 0;\"></div>");
			bf.append("\n");
			bf.append("				<div>");
			bf.append("\n");
			bf.append("					<form id=\"" + tablename + "form\" action='' method=\"post\">");
			bf.append("\n");
			//表主键
			bf.append("						<input type=\"hidden\" name=\"" + Tools.toLowerCase(xid) + "\" id='"+Tools.toLowerCase(xid)+"' />");
			bf.append("\n");
			
			
			
			
			//###########################  begin
			if(tcolus != null && tcolus.size() > 0){
			bf.append("						<table style='font-size: 12px;'>");
			bf.append("\n");
				for(int i=0; i<tcolus.size(); i++){
					Map tm = (Map) tcolus.get(i);
					String column_name = tm.get("column_name").toString();
					String comments = tm.get("comments") != null ? tm.get("comments").toString() : "";
					if(comments != null && comments.lastIndexOf("add:1") > -1){
			bf.append("							<tr><td>" + Tools.beformFirst(comments) + ":</td><td><input class=\"easyui-validatebox xtext\" name='" + Tools.toLowerCase(column_name) + "' id='" + Tools.toLowerCase(column_name) + "' data-options=\"required:true\" /></td></tr>");
			bf.append("\n");
					}
				}
			bf.append("						</table>");
			bf.append("\n");
			}
			//###########################  end
			
			
			bf.append("					</form>");
			bf.append("\n");
			bf.append("					<div style=\"text-align: center;\">");
			bf.append("\n");
			bf.append("						<a id='badd' style=\"display: none;\" href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconcls='icon-ok' onclick=\"addsubmitForm()\">添加</a>");
			bf.append("\n");
			bf.append("						<a id='bupdate' style=\"display: none;\" href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconcls='icon-ok' onclick=\"updatesubmitForm()\">修改</a>");
			bf.append("\n");
			bf.append("						<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconcls='icon-cancel' onclick=\"closeWin()\">关闭</a>");
			bf.append("\n");
			bf.append("					</div>");
			bf.append("\n");
			bf.append("				</div>");
			bf.append("\n");
			bf.append("			</div>");
			bf.append("\n");
			bf.append("	</body>");
			bf.append("\n");
			bf.append("</html>");
			
		return bf.toString();
	}
}
