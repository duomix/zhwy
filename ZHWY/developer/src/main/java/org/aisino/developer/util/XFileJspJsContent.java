package org.aisino.developer.util;

import java.util.List;
import java.util.Map;

import org.aisino.developer.DB;




/**
 * 获得Jsp js 文件内容
 * @author Administrator
 *
 */
public class XFileJspJsContent {
	
	/**
	 * 获取jsp js内容
	 * @param path
	 * @param tablename
	 * @return
	 */
	public static String getJspJsContent(String path,String tablename,String databasename){
		if(path.indexOf("#") > -1){
			String ts[] = path.split("#");
			path = ts[1];
		}
		String xid = DB.getXidName(tablename,databasename);
		//加载table   columns模型   begin 
		List tablecolumns = DB.getTableNameColus(tablename,databasename);
		//查询表所有字段 并判断是否生成add form字段   对应jsp页面  
		List tcolus = DB.getTableNameColus(tablename,databasename);
		tablename = Tools.Format(tablename);
		tablename = Tools.toLowerCase(tablename);
		StringBuffer bf = new StringBuffer();
		bf.append("$(function() {");
		bf.append("\n");
		bf.append("		ajaxDataTable( {});");
		bf.append("\n");
		bf.append("		//创建 添加/修改window");
		bf.append("\n");
		bf.append("		$('#addorupdatewin').window( {");
		bf.append("\n");
		bf.append("			title : '添加/修改信息',");
		bf.append("\n");
		bf.append("			width : 400,");
		bf.append("\n");
		bf.append("			height : 350,");
		bf.append("\n");
		bf.append("			minimizable : false,");
		bf.append("\n");
		bf.append("			maximizable : false,");
		bf.append("\n");
		bf.append("			closable : false,");
		bf.append("\n");
		bf.append("			closed : true,");
		bf.append("\n");
		bf.append("			shadow : true,");
		bf.append("\n");
		bf.append("			modal : true");
		bf.append("\n");
		bf.append("		});");
		bf.append("\n");
		bf.append("	});");
		bf.append("\n");
		bf.append("//加载数据table");
		bf.append("\n");
		bf.append("function ajaxDataTable(ajaxParamsArr) {");
		bf.append("\n");
		bf.append("$('#list_" + tablename + "_table').datagrid(");
		bf.append("\n");
		bf.append("	{");
		bf.append("\n");
		bf.append("		toolbar : ['-',");
		bf.append("\n");
		bf.append("			{ // 正上方工具栏");
		bf.append("\n");
		bf.append("				text : '添加',");
		bf.append("\n");
		bf.append("				iconcls : 'icon-add',");
		bf.append("\n");
		bf.append("				handler : function() {");
		bf.append("\n");
		bf.append("					$('#addorupdatewin').window('open');");
		bf.append("\n");
		bf.append("					$('#" + tablename + "form').form('clear');");
		bf.append("\n");
		bf.append("					$('#badd').attr('style','display: inline-block');");
		bf.append("\n");
		bf.append("					$('#bupdate').attr('style', 'display: none');");
		bf.append("\n");
		bf.append("				}");
		bf.append("\n");
		bf.append("			},'-',{");
		bf.append("\n");
		bf.append("				text : '编辑',");
		bf.append("\n");
		bf.append("				iconcls : 'icon-edit',");
		bf.append("\n");
		bf.append("				handler : function() {");
		bf.append("\n");
		bf.append("					var rows = $('#list_" + tablename + "_table').datagrid('getSelections');");
		bf.append("\n");
		bf.append("					if (rows == \"\") {");
		bf.append("\n");
		bf.append("						$.messager.alert('警告', '请选择要修改的项目!', 'info');return;");
		bf.append("\n");
		bf.append("					}");
		bf.append("\n");
		bf.append("					if (rows.length > 1) {");
		bf.append("\n");
		bf.append("						$.messager.alert('警告', '修改的项目只能够一项!','info');return;");
		bf.append("\n");
		bf.append("					}");
		bf.append("\n");
		
		bf.append("					//获得选中行数据");
		bf.append("\n");
		bf.append("					var datas = rows[0];");
		bf.append("\n");
		//修改主键赋值
		bf.append("					$('#" + Tools.toLowerCase(xid) + "').val(datas." + Tools.toLowerCase(xid) + ");");
		bf.append("\n");
		
		//###########################  begin
		if(tcolus != null && tcolus.size() > 0){
			for(int i=0; i<tcolus.size(); i++){
				Map tm = (Map) tcolus.get(i);
				String column_name = tm.get("column_name").toString();
				String comments = tm.get("comments") != null ? tm.get("comments").toString() : "";
				if(comments != null && comments.lastIndexOf("add:1") > -1){
		bf.append("					$('#" + Tools.toLowerCase(column_name) + "').val(datas." + Tools.toLowerCase(column_name) + ");");
		bf.append("\n");
				}
			}
		}
		//###########################  end
		bf.append("					$('#addorupdatewin').window('open');");
		bf.append("\n");
		bf.append("					$('#badd').attr('style', 'display: none');");
		bf.append("\n");
		bf.append("					$('#bupdate').attr('style','display: inline-block');");
		bf.append("\n");
		bf.append("				}");
		bf.append("\n");
		bf.append("			},'-',{");
		bf.append("\n");
		bf.append("				text : '删除',");
		bf.append("\n");
		bf.append("				iconcls : 'icon-remove',");
		bf.append("\n");
		bf.append("				handler : function() {");
		bf.append("\n");
		bf.append("					var ids = '';");
		bf.append("\n");
		bf.append("					var rows = $('#list_" + tablename + "_table').datagrid('getSelections');");
		bf.append("\n");
		bf.append("					if (rows.length < 1) {");
		bf.append("\n");
		bf.append("						$.messager.alert('警告', '选择需要操作的项目!', 'info');return;");
		bf.append("\n");
		bf.append("					} else {");
		bf.append("\n");
		bf.append("						if (rows.length == 1) {");
		bf.append("\n");
		bf.append("							ids = rows[0]."+xid+" + \",\";");
		bf.append("\n");
		bf.append("						} else {");
		bf.append("\n");
		bf.append("							for ( var i = 0; i < rows.length; i++) {");
		bf.append("\n");
		bf.append("								ids += rows[i]."+xid+" + \",\";");
		bf.append("\n");
		bf.append("							}");
		bf.append("\n");
		bf.append("						}");
		bf.append("\n");
		bf.append("					}");
		bf.append("\n");
		bf.append("					deletedata(ids);");
		bf.append("\n");
		bf.append("				}");
		bf.append("\n");
		bf.append("			} ],");
		bf.append("\n");
		bf.append("		title : '表格信息', //标题");
		bf.append("\n");
		bf.append("		method : 'post', ");
		bf.append("\n");
		bf.append("		iconcls : 'pic_43', //图标  ");
		bf.append("\n");
		bf.append("		singleSelect : false, //多选    ");
		bf.append("\n");
		bf.append("		height : 500, //高度  ");
		bf.append("\n");
		bf.append("		fitColumns : true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。   ");
		bf.append("\n");
		bf.append("		striped : true, //奇偶行颜色不同  ");
		bf.append("\n");
		bf.append("		url : '"+tablename+"/findlist.do', //数据来源   ");
		bf.append("\n");
		bf.append("		remoteSort : true, //服务器端排序   ");
		bf.append("\n");
		bf.append("		idField : '" + Tools.toLowerCase(xid) + "', //主键字段  ");
		bf.append("\n");
		bf.append("		queryParams : ajaxParamsArr, //查询条件  ");
		bf.append("\n");
		bf.append("		pagination : true, //显示分页   ");
		bf.append("\n");
		bf.append("		rownumbers : true, //显示行号  ");
		bf.append("\n");
		bf.append("		autoRowHeight : false,//定义设置行的高度，根据该行的内容。设置为false可以提高负载性能。  ");
		bf.append("\n");
		bf.append("		pageSize : 20,");
		bf.append("\n");
		bf.append("		columns : [[");
		bf.append("\n");
		
		if(tablecolumns != null && tablecolumns.size() > 0){
			for(int i=0; i<tablecolumns.size(); i++){
				Map tm = (Map) tablecolumns.get(i);
				String column_name = tm.get("column_name").toString();
				String comments = tm.get("comments") != null ? tm.get("comments").toString() : "";
				if(i == 0){
		bf.append("					{");
		bf.append("\n");
		bf.append("						field : 'ck',");
		bf.append("\n");
		bf.append("						checkbox : true,");
		bf.append("\n");
		bf.append("						width : 2,");
		bf.append("\n");
		bf.append("						hidden : false");
		bf.append("\n");
		bf.append("					}");
		bf.append("\n");
				}else{
		bf.append("					,{");
		bf.append("\n");
		bf.append("						field : '" + Tools.toLowerCase(column_name) + "',");
		bf.append("\n");
		bf.append("						title : '" + Tools.beformFirst(comments) + "',");
		bf.append("\n");
		bf.append("						width : 100,");
		bf.append("\n");
		bf.append("						sortable : true,");
		bf.append("\n");
		bf.append("						hidden : false,");
		bf.append("\n");
		bf.append("						formatter : function(value, row, index) {return row." + Tools.toLowerCase(column_name) + ";}");	
		bf.append("\n");
		bf.append("					}");	
		bf.append("\n");
				}
			}
		}
		//加载table   columns模型   end 
		bf.append("				  ]],");
		bf.append("\n");
		bf.append("		onLoadSuccess : function() {");
		bf.append("\n");
		bf.append("			$('#list_" + tablename + "_table').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  ");
		bf.append("\n");
		bf.append("	}");
		bf.append("\n");
		bf.append("	});");
		bf.append("\n");
		bf.append("}");
		bf.append("\n");
		
		bf.append("//提交 添加字典form");
		bf.append("\n");
		bf.append("function addsubmitForm() {");
		bf.append("\n");
		bf.append("	if (!$('#" + tablename + "form').form('validate')) {");
		bf.append("\n");
		bf.append("		$.messager.alert('警告', '数据不合法!', 'info');return false;");
		bf.append("\n");
		bf.append("	}");
		bf.append("\n");
		bf.append("	var data = $('#" + tablename + "form').serialize();");
		bf.append("\n");
		bf.append("	$.ajax({");
		bf.append("\n");
		bf.append("			type : \"POST\",");
		bf.append("\n");
		bf.append("			url : '" + tablename + "/add.do',");
		bf.append("\n");
		bf.append("			data : data,");
		bf.append("\n");
		bf.append("			success : function(msg) {");
		bf.append("\n");
		bf.append("				if (msg == '-1') {");
		bf.append("\n");
		bf.append("					$.messager.alert('警告', '保存失败!', 'info');");
		bf.append("\n");
		bf.append("				} else {");
		bf.append("\n");
		bf.append("					closeWin();reload" + tablename + "Table(null);");
		bf.append("\n");
		bf.append("				}");
		bf.append("\n");
		bf.append("			}");
		bf.append("\n");
		bf.append("		});");
		bf.append("\n");
		bf.append("	}");
		bf.append("\n");
		
		bf.append("//删除字典信息");
		bf.append("\n");
		bf.append("function deletedata(id) {");
		bf.append("\n");
		bf.append("	$.messager.confirm('Confirm', '是否确定删除?', function(r) {");
		bf.append("\n");
		bf.append("	if (r) {");
		bf.append("\n");
		bf.append("		$.ajax( {");
		bf.append("\n");
		bf.append("			type : \"POST\",");
		bf.append("\n");
		bf.append("			url : '" + tablename + "/delete.do',");
		bf.append("\n");
		bf.append("			data : '"+xid+"=' + id,");
		bf.append("\n");
		bf.append("			success : function(msg) {");
		bf.append("\n");
		bf.append("				if (msg == '-1') {");
		bf.append("\n");
		bf.append("					$.messager.alert('警告', '删除失败!', 'info');");
		bf.append("\n");
		bf.append("				} else {");
		bf.append("\n");
		bf.append("					reload" + tablename + "Table({});");
		bf.append("\n");
		bf.append("				}");
		bf.append("\n");
		bf.append("			}");
		bf.append("\n");
		bf.append("		});");
		bf.append("\n");
		bf.append("	}");
		bf.append("\n");
		bf.append("	});");
		bf.append("\n");
		bf.append("}");
		bf.append("\n");
		
		bf.append("//修改字典信息 提交");
		bf.append("\n");
		bf.append("function updatesubmitForm() {");
		bf.append("\n");
		bf.append("	if (!$('#" + tablename + "form').form('validate')) {");
		bf.append("\n");
		bf.append("		$.messager.alert('警告', '数据不合法!', 'info');return false;");
		bf.append("\n");
		bf.append("	}");
		bf.append("\n");
		bf.append("	var data = $('#" + tablename + "form').serialize();");
		bf.append("\n");
		bf.append("	$.ajax( {");
		bf.append("\n");
		bf.append("		type : \"POST\",");
		bf.append("\n");
		bf.append("		url : '" + tablename + "/update.do',");
		bf.append("\n");
		bf.append("		data : data,");
		bf.append("\n");
		bf.append("		success : function(msg) {");
		bf.append("\n");
		bf.append("			if (msg == '-1') {");
		bf.append("\n");
		bf.append("				$.messager.alert('警告', '修改失败!', 'info');");
		bf.append("\n");
		bf.append("			} else {");
		bf.append("\n");
		bf.append("				closeWin();reload" + tablename + "Table(null);");
		bf.append("\n");
		bf.append("		}");
		bf.append("\n");
		bf.append("	}");
		bf.append("\n");
		bf.append("	});");
		bf.append("\n");
		bf.append("}");
		bf.append("\n");
		
		bf.append("//刷新dicTable数据");
		bf.append("\n");
		bf.append("function reload" + tablename + "Table(t) {$('#list_" + tablename + "_table').datagrid('reload', t);}");
		bf.append("\n");
		
		bf.append("//关闭 添加 or 修改Window");
		bf.append("\n");
		bf.append("function closeWin() {$('#addorupdatewin').window('close');}");
		bf.append("\n");
		
		return bf.toString();
	}
}
