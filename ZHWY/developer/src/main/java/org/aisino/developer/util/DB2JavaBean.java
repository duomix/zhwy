package org.aisino.developer.util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import org.aisino.developer.DB;

/**
 * 生成VO
 * @author Administrator
 *
 */
public class DB2JavaBean {

	private String driver = ""; // 驱动
	private String url = ""; // 数据库访问串
	private String userName = ""; // 数据库用户名
	private String password = ""; // 数据库密码
	private String tableName = ""; // 要生成jopo对象的表名,使用;进行分割

	public DB2JavaBean() {
	}

	public DB2JavaBean(boolean init,String xtableName) {
		if (init) {
			this.driver = DB.driver;
			this.url = DB.url;
			this.userName = DB.userName;
			this.password = DB.password;
			this.tableName = xtableName;
		}

	}


	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDriver() {
		return this.driver;
	}

	public String getUrl() {
		return this.url;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getPassword() {
		return this.password;
	}

	public String getTableName() {
		return this.tableName;
	}
	
	/**
	 * 
	 * @param path
	 * @param ObjectTypeOrCommonlyType
	 */
	public void init(String path,int ObjectTypeOrCommonlyType) {
		try {
			String pac = path;
			if(path.indexOf("#") > -1){
				String ts[] = path.split("#");
				pac = ts[1];
			}
			path = XAP.getAPVo(path);
			Class.forName(this.driver).newInstance();
			Connection conn = DriverManager.getConnection(this.url,
					this.userName, this.password);
			String[] tables = new String[0];
			ArrayList tableal = new ArrayList(20);
			tables = this.tableName.split(";");
			String strType;
			String strName;
			String className;
			String nameSect;
			StringBuilder tbn = new StringBuilder();
			StringBuilder tstr1 = new StringBuilder();
			StringBuilder tstr2 = new StringBuilder();
			//pojo保存路径
			File file = new File(path);
			if (!file.exists())
				file.mkdir();
			if (!file.isDirectory())
				file.mkdir();
			for (int i = 0; i < tables.length; i++) {
				nameSect = tables[i];
//				for (String ns : nameSect) {
					tbn.append(nameSect.substring(0, 1).toUpperCase()
							+ nameSect.substring(1).toLowerCase());
//				}
				className = tbn.toString();
				className = Tools.Format(className);
				tbn.delete(0, tbn.length());
				tstr1.append("package " + pac + ".vo;");
				tstr1.append("\n");
				tstr1.append("import org.aisino.core.base.BaseDomain; ");
				tstr1.append("\n");
				tstr1.append("public class " + className
						+ " extends BaseDomain { ");
				tstr1.append("\n");
				tstr1.append(" private static final long serialVersionUID = 1L;");
				tstr1.append("\n");
				
				
				tstr1.append(" private String xid;");
				tstr1.append("\n");
				tstr1.append("  public String getXid() {");
				tstr1.append("\n");
				tstr1.append("  	return xid;");
				tstr1.append("\n");
				tstr1.append("  }");
				tstr1.append("\n");
				tstr1.append("  public void setXid(String xid) {");
				tstr1.append("\n");
				tstr1.append("  	this.xid = xid;");
				tstr1.append("\n");
				tstr1.append("  }");
				tstr1.append("\n");
				try {
					System.out.println("VO处理表明：" + tables[i]);
					Statement statement = conn.createStatement();
					ResultSet rs = statement.executeQuery("select * from "
							+ tables[i]);
					ResultSetMetaData rsd = rs.getMetaData();
					int cc = rsd.getColumnCount();
					for (int j = 1; j <= cc; j++) {
						//匹配列类型
//						if (ObjectTypeOrCommonlyType == StaticVar.OBJECTTYPE) {
//							strType = this.getObjectType(rsd.getColumnType(j));
//						} else {
//							strType = this.getCommonlyType(j);
//						}
						//自定义为String类型
						strType = "String";
						
						if (strType == null)
							continue;
						strName = rsd.getColumnName(j);
						tstr1.append(" private " + strType + " "
								+ strName.toLowerCase() + ";");
						tstr1.append("\n");
						tstr2.append(" public void set"
								+ strName.substring(0, 1).toUpperCase()
								+ strName.substring(1).toLowerCase() + "("
								+ strType + " " + strName.toLowerCase() + "){");
						tstr2.append("\n");
						tstr2.append("    this." + strName.toLowerCase()
								+ " = " + strName.toLowerCase() + ";");
						tstr2.append("\n");
						tstr2.append(" }");
						tstr2.append("\n");
						tstr2.append(" public " + strType + " get"
								+ strName.substring(0, 1).toUpperCase()
								+ strName.substring(1).toLowerCase() + "(){");
						tstr2.append("\n");
						tstr2.append("    return this." + strName.toLowerCase()
								+ ";");
						tstr2.append("\n");
						tstr2.append(" }");
						tstr2.append("\n");

					}
					rs.close();
					statement.close();

				} catch (Exception tableE) {
					tableE.printStackTrace();
				}
				tstr2.append("} ");
				tstr2.append("\n");
				tstr1.append(tstr2.toString());
				tstr1.append("\n");
				file = new File(path + "/" +className + ".java");
				FileWriter fw = new FileWriter(file);
				fw.write(tstr1.toString());
				fw.flush();
				fw.close();
				tstr1.delete(0, tstr1.length());
				tstr2.delete(0, tstr2.length());
			}
			conn.close();
			System.out.println("OK");
		} catch (Exception driverE) {
			driverE.printStackTrace();
		}

	}

	public String getObjectType(int type) {
		switch (type) {
		case Types.ARRAY:
			return null;
		case Types.BIGINT:
			return "Long";
		case Types.BINARY:
			return null;
		case Types.BIT:
			return "Byte";
		case Types.BLOB:
			return "Blob";
		case Types.BOOLEAN:
			return "Boolean";
		case Types.CHAR:
			return "String";
		case Types.CLOB:
			return "Clob";
		case Types.DATALINK:
			return null;
		case Types.DATE:
			return "Date";
		case Types.DECIMAL:
			return "Double";
		case Types.DISTINCT:
			return null;
		case Types.DOUBLE:
			return "Double";
		case Types.FLOAT:
			return "Float";
		case Types.INTEGER:
			return "Integer";
		case Types.NUMERIC:
			return "Integer";
		case Types.JAVA_OBJECT:
			return null;
		case Types.LONGVARBINARY:
			return null;
		case Types.LONGVARCHAR:
			return null;
		case Types.NULL:
			return null;
		case Types.OTHER:
			return null;
		case Types.REAL:
			return null;
		case Types.REF:
			return null;
		case Types.SMALLINT:
			return "Short";
		case Types.STRUCT:
			return null;
		case Types.TIME:
			return "Time";
		case Types.TIMESTAMP:
			return "Timestamp";
		case Types.TINYINT:
			return "Short";
		case Types.VARBINARY:
			return null;
		case Types.VARCHAR:
			return "String";
		default:
			return null;
		}
	}

	public String getCommonlyType(int type) {
		switch (type) {
		case Types.ARRAY:
			return null;
		case Types.BIGINT:
			return "long";
		case Types.BINARY:
			return null;
		case Types.BIT:
			return "byte";
		case Types.BLOB:
			return "String";
		case Types.BOOLEAN:
			return "boolean";
		case Types.CHAR:
			return "String";
		case Types.CLOB:
			return "String";
		case Types.DATALINK:
			return null;
		case Types.DATE:
			return "Date";
		case Types.DECIMAL:
			return "double";
		case Types.DISTINCT:
			return null;
		case Types.DOUBLE:
			return "double";
		case Types.FLOAT:
			return "float";
		case Types.INTEGER:
			return "int";
		case Types.NUMERIC:
			return "int";
		case Types.JAVA_OBJECT:
			return null;
		case Types.LONGVARBINARY:
			return null;
		case Types.LONGVARCHAR:
			return null;
		case Types.NULL:
			return null;
		case Types.OTHER:
			return null;
		case Types.REAL:
			return null;
		case Types.REF:
			return null;
		case Types.SMALLINT:
			return "short";
		case Types.STRUCT:
			return null;
		case Types.TIME:
			return "Time";
		case Types.TIMESTAMP:
			return "Timestamp";
		case Types.TINYINT:
			return "short";
		case Types.VARBINARY:
			return null;
		case Types.VARCHAR:
			return "String";
		default:
			return null;
		}
	}

	/*public static void main(String[] args) {
		String path = "org.aisino.demo";
		DB2JavaBean d2j = new DB2JavaBean(true,"aos_user");
		d2j.init(path,StaticVar.OBJECTTYPE);
		System.out.println("OK");
	}*/

}
