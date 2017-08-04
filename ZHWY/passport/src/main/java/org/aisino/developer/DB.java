package org.aisino.developer;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DB {
	//驱动com.mysql.jdbc.Driver;oracle.jdbc.driver.OracleDriver
	public static String driver = "com.mysql.jdbc.Driver"; 
	public static String url = "jdbc:mysql://127.0.0.1:3306/zhwy?useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true";
	public static String userName = "root"; // 数据库用户名
	public static String password = "123456"; // 数据库密码
	
	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url,
					userName, password);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	/**
	 * 获取表 主键字段
	 * @param tablename
	 * @return
	 */
	public static String getXidName(String tablename,String databasename){
		Connection conn = DB.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String xidname = null;
		try {
			if(conn != null){
				stmt = conn.createStatement();
				//jdbc获取表主键(只考虑一个主键情况)
				String s1 = "SHOW FULL FIELDS FROM "+tablename.toLowerCase()+" FROM " + databasename.toLowerCase();
				//System.out.println("查询表主键sql:"+s1);
				rs = stmt.executeQuery(s1);
				while(rs.next()){
					String column_name = rs.getString(1).toLowerCase();
					String data_type = rs.getString(2);
					String key = rs.getString(5);
					if("PRI".equals(key)){
						xidname = column_name;
						break;
					}
				}
				  rs.close();
			      stmt.close();
			      conn.close();
			}else{
				System.out.println("数据库连接错误!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return xidname;
	}
	
	
	/**
	 * 获取表 所有字段集合
	 * @param tablename
	 * @return
	 */
	public static List getTableNameColus(String tablename,String databasename){
		//表所有列集合
		List cl = new ArrayList(); 
		Connection conn = DB.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			if(conn != null){
				stmt = conn.createStatement();
				//jdbc获取表所有列
				String s2 = "SHOW FULL FIELDS FROM "+tablename.toLowerCase()+" FROM " + databasename.toLowerCase();
				System.out.println("查询表所有列sql:"+s2);
				rs = stmt.executeQuery(s2);
				Map cm = null;
				while(rs.next()){
					cm = new HashMap();
					String column_name = rs.getString(1).toLowerCase();
					String data_type = rs.getString(2).toLowerCase();
					Object data_default = rs.getObject(6);
					String comments = rs.getString(9);
					String key = rs.getString(5);
					cm.put("column_name", column_name);
					cm.put("data_type", data_type);
					cm.put("data_default", data_default);
					cm.put("comments", new String(rs.getBytes(9),"utf-8"));
					cl.add(cm);
				}
				  rs.close();
			      stmt.close();
			      conn.close();
			}else{
				System.out.println("数据库连接错误!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cl;
	}
	
	/*public static void main(String[] args) {
		List t = getTableNameColus("aos_constants","aosx");
		String id = getXidName("aos_constants","aosx");
		System.out.println("表主键:"+id);
		System.out.println("表字段集合:"+t);
	}*/
}
