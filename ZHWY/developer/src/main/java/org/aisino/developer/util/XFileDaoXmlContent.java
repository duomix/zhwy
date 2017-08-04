package org.aisino.developer.util;

import java.util.List;
import java.util.Map;

import org.aisino.developer.DB;


/**
 * 获得Dao xml 文件内容
 * @author Administrator
 *
 */
public class XFileDaoXmlContent {
	
	/**
	 * 获取dao xml内容
	 * @param path
	 * @param tablename
	 * @param i
	 * @return
	 */
	public static String getIContent(String path,String tablename,Map map,String databasename){
		if(path.indexOf("#") > -1){
			String ts[] = path.split("#");
			path = ts[1];
		}
		tablename = Tools.FirstUpperCase(tablename);
		//表所有列集合
		List cl = DB.getTableNameColus(tablename,databasename);
		//主键列明
		String xidname = DB.getXidName(tablename,databasename);
		String xidname_type = null;
		
		//特殊查询字段处理
		String selectn = map.get("selectn") != null ? map.get("selectn").toString() : "";
		//连接表
		String tablen = map.get("tablen") != null ? map.get("tablen").toString() : "";
		
		StringBuffer bf = new StringBuffer();
			//定义xml头
			bf.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			bf.append("\n");
			bf.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
			bf.append("\n");
			String sql_tablename = tablename.toLowerCase();//查询用表明不需要处理
			tablename = Tools.Format(tablename);
			String idao = XAP.getAPIDaoBackage(path) + "I" + tablename + "Dao";
			bf.append("<mapper namespace=\""+idao+"\">");//接口路径
			bf.append("\n");
//			bf.append("	<typeAlias alias=\"xmap\" type=\"java.util.HashMap\" />");
//			bf.append("\n");
//			bf.append("<typeAlias alias=\""+tablename.toLowerCase()+"\" type=\""+path+".vo."+tablename+"\" />");
//			bf.append("\n");
				//定义sql 区   begin
					
					//分页查询和总条数
					StringBuffer t1 = new StringBuffer();
					StringBuffer t2 = new StringBuffer();
					
					StringBuffer t3 = new StringBuffer();
					StringBuffer t4 = new StringBuffer();
					
					StringBuffer t5 = new StringBuffer();
					if(cl != null){
						for(int i=0;i<cl.size();i++){
							Map m =(Map) cl.get(i);
							//是否有默认值 null没有
							Object od  = m.get("data_default");
							String sc = m.get("column_name").toString();
							String xsc = null;
							String st = m.get("data_type").toString();
							//主键类型
							if(xidname.equals(sc)){
								xidname_type = st;
							}
							
							if("date".equals(st)){
								xsc = "date_format(t."+sc+", '%Y-%m-%d %H:%i:%s')";
							}else{
								t2.append("			<if test=\""+sc+" != null and "+sc+" !=''\"> and ");
								t2.append("\n");
								if("number".equals(st) || st.indexOf("int") > -1){
									t2.append("				t."+sc+" = ${"+sc+"}");
								}else{
									t2.append("				t."+sc+" = #{"+sc+"}");
								}
								t2.append("\n");
								t2.append("			</if>");
								t2.append("\n");
							}
							
							if(i == 0){
								if("date".equals(st)){
									t1.append(xsc + " as "+sc+" ");
								}else{
									t1.append("t."+sc);
								}
//										t3.append("		<isNotEmpty prepend=\"\" property=\""+sc+"\">"+sc+"</isNotEmpty>");
//										if("number".equals(st)){
//											t4.append("		<isNotEmpty prepend=\"\" property=\""+sc+"\">$"+sc+"$</isNotEmpty>");
//										}else if("date".equals(st)){
//											t4.append("		<isNotEmpty prepend=\"\" property=\""+sc+"\">str_to_date(#"+sc+"#, '%Y-%m-%d %H:%i:%s')</isNotEmpty>");
//										}else{
//											t4.append("		<isNotEmpty prepend=\"\" property=\""+sc+"\">#"+sc+"#</isNotEmpty>");
//										}
								
							}else{
								if("date".equals(st)){
									t1.append(", " + xsc + " as "+sc+" ");
								}else{
									t1.append(", t." + sc);
								}
							}
							
							if(!sc.equals(xidname)){
								t3.append("		<if test=\""+sc+" != null and "+sc+" !=''\"> , "+sc+"</if>");
								if("number".equals(st)  || st.indexOf("int") > -1){
									t4.append("		<if test=\""+sc+" != null and "+sc+" !=''\"> , ${"+sc+"}</if>");
								}else if("date".equals(st)){
									t4.append("		<if test=\""+sc+" != null and "+sc+" !=''\"> , str_to_date(#{"+sc+"}, '%Y-%m-%d %H:%i:%s')</if>");
								}else{
									t4.append("		<if test=\""+sc+" != null and "+sc+" !=''\"> , #{"+sc+"}</if>");
								}
							}
							t3.append("\n");
							t4.append("\n");
							
							if(!sc.equals(xidname)){
								if("date".equals(st)){
									t5.append("				<if test=\""+sc+" != null and "+sc+" !=''\"> , "+sc+" = str_to_date(#{"+sc+"}, '%Y-%m-%d %H:%i:%s')</if>");
								}else if("number".equals(st)  || st.indexOf("int") > -1){
									t5.append("				<if test=\""+sc+" != null and "+sc+" !=''\"> , "+sc+" = ${"+sc+"}</if>");
								}else{
									t5.append("				<if test=\""+sc+" != null and "+sc+" !=''\"> , "+sc+" = #{"+sc+"}</if>");
								}
							}
							t5.append("\n");
						}
					}
					bf.append("\n");
					
					/*bf.append("	<select id=\"query"+tablename+"ListPage\" parameterType=\"map\" resultType=\"xmap\" >");
					bf.append("\n");
					bf.append("		select @rownum:=@rownum+1 AS rn,A.* from( ");
					bf.append("\n");
					bf.append("			select ");
					bf.append("\n");
					bf.append("				" + selectn + t1.toString());
					bf.append("\n");
					bf.append("			from " + tablename + " t ");
					bf.append("\n");
					bf.append("			" + tablen);
					bf.append("\n");
					bf.append("			where 1 = 1  ");
					bf.append("\n");
					bf.append(t2.toString());
					bf.append("\n");
					bf.append("		limit  ${q_number - 1} , ${rows}) A,(SELECT @rownum:=${q_number - 1}) r");
					bf.append("\n");
					bf.append("	</select>");
					bf.append("\n");
					bf.append("	<select id=\"query"+tablename+"ListCount\" parameterType=\"map\" resultType=\"java.lang.Integer\">");
					bf.append("\n");
						bf.append("		select count(*) from "+tablename+" t where 1 = 1");
						bf.append("\n");
						bf.append(t2.toString());
					bf.append("	</select>");
					bf.append("\n");
					bf.append("\n");*/
					
					
					//查询所有满足条件的List集合
					bf.append("	<select id=\"findList\" parameterType=\""+tablename+"\" resultType=\""+tablename+"\" >");
					bf.append("\n");
						bf.append("		select ");
						bf.append("\n");
						bf.append("				" + selectn + t1.toString());
						bf.append("\n");
						bf.append("			from " + sql_tablename + " t ");
						bf.append("\n");
						bf.append("			" + tablen);
						bf.append("\n");
						bf.append("			where 1 = 1  ");
						bf.append("\n");
						bf.append(t2.toString());
					bf.append("	</select>");
					bf.append("\n");
					bf.append("\n");
					
					//查询一条信息
					bf.append("	<select id=\"get\" parameterType=\""+tablename+"\" resultType=\""+tablename+"\" >");
					bf.append("\n");
						bf.append("		select ");
						bf.append("\n");
						bf.append("				" + selectn + t1.toString());
						bf.append("\n");
						bf.append("			from " + sql_tablename + " t ");
						bf.append("\n");
						bf.append("			" + tablen);
						bf.append("\n");
						bf.append("			where 1 = 1  ");
						bf.append("\n");
						bf.append(t2.toString());
					bf.append("	</select>");
					bf.append("\n");
					bf.append("\n");
					
					//添加
					bf.append("	<insert id=\"insert\" parameterType=\""+tablename+"\">");
					bf.append("\n");
						bf.append("		insert into "+sql_tablename+"");
						bf.append("\n");
						bf.append("			("+xidname+" "+t3.toString()+"		)");
						bf.append("\n");
						bf.append("		values ");
						if("number".equals(xidname_type)){
							bf.append("			(${"+xidname+"} "+t4.toString()+"		)");
						}else{
							bf.append("			(#{"+xidname+"} "+t4.toString()+"		)");
						}
						bf.append("\n");
						bf.append("\n");
					bf.append("	</insert>");
					bf.append("\n");
					bf.append("\n");
					
					
					//修改
					bf.append("	<update id=\"update\" parameterType=\""+tablename+"\">");
					bf.append("\n");
						bf.append("		update "+sql_tablename+" ");
						bf.append("\n");
						if("number".equals(xidname_type)){
							bf.append("			set "+xidname+"   = ${"+xidname+"}");
						}else{
							bf.append("			set "+xidname+"   = #{"+xidname+"}");
						}
						bf.append("\n");
						bf.append(t5.toString());
						if("number".equals(xidname_type)){
							bf.append("		where "+xidname+" = ${"+xidname+"}");
						}else{
							bf.append("		where "+xidname+" = #{"+xidname+"}");
						}
						bf.append("\n");
					bf.append("	</update>");
					bf.append("\n");
					bf.append("\n");
					
					
					//删除
					bf.append("	<delete id=\"delete\" parameterType=\""+tablename+"\">");
					bf.append("\n");
						if("number".equals(xidname_type)){
							bf.append("		delete from "+sql_tablename+"  where "+xidname+" = ${"+xidname+"}");
						}else{
							bf.append("		delete from "+sql_tablename+"  where "+xidname+" = #{"+xidname+"}");
						}
						bf.append("\n");
					bf.append("	</delete>");
					bf.append("\n");
					bf.append("\n");
			
				//定义sql 区   end
			bf.append("</mapper>");
			bf.append("\n");
		return bf.toString();
	}
}
