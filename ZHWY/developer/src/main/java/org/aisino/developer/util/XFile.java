package org.aisino.developer.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XFile {
	
	/**
	 * 在指定路径下创建dao文件
	 * @param path
	 * @param n
	 * @param i 3：xml   1:idao   2：daoimpl
	 */
	public static void createDaoFile(String path,String n,int i){
		if(n != null && path != null){
			if(3 == i){
				path = path + "//" + Tools.Format(Tools.FirstUpperCase(n)) + "Dao.xml";
			}else if(1 == i){
				path = path + "//I" + Tools.Format(Tools.FirstUpperCase(n)) + "Dao.java";
			}else if(2 == i){
				path = path + "//" + Tools.Format(Tools.FirstUpperCase(n)) + "DaoImpl.java";
			}
			File f1 = new File(path);
			try {
				//f1.delete();
				f1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * dao 生成相应文件内容
	 * @param path 包名
	 * @param tablename 表明
	 * @param i 3：xml   1:idao   2：daoimpl
	 */
	public static void createDaoFileContent(String path,String tablename,int i,Map m,String databasename){
		tablename = Tools.FirstUpperCase(tablename);
		StringBuffer bf = new StringBuffer();
		if(3 == i){//xml
			String p = XAP.getAPDao(path, 3) + "//" + Tools.Format(tablename) + "Dao.xml";
			writeFile(p,org.aisino.developer.util.XFileDaoXmlContent.getIContent(path, tablename,m,databasename));
		}else if(1 == i){//I
			String p = XAP.getAPDao(path, 1) + "//I" + Tools.Format(tablename) + "Dao.java";
			writeFile(p,org.aisino.developer.util.XFileDaoContent.getIContent(path, tablename, 1));
		}else if(2 == i){//impl
			String p = XAP.getAPDao(path, 2) + "//" + Tools.Format(tablename) + "DaoImpl.java";
			writeFile(p,org.aisino.developer.util.XFileDaoContent.getIContent(path, tablename, 2));
		}
	}
	
	/**
	 * 在指定路径下创建Service文件
	 * @param path
	 * @param n
	 * @param i 1:iService   2：Serviceimpl
	 */
	public static void createServiceFile(String path,String n,int i){
		if(n != null && path != null){
			if(1 == i){
				path = path + "//I" + Tools.Format(Tools.FirstUpperCase(n)) + "Service.java";
			}else if(2 == i){
				path = path + "//" + Tools.Format(Tools.FirstUpperCase(n)) + "ServiceImpl.java";
			}
			File f1 = new File(path);
			try {
				//f1.delete();
				f1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Service 生成相应文件内容
	 * @param path 包名
	 * @param tablename 表明
	 * @param i  1:idao   2：daoimpl
	 */
	public static void createServiceFileContent(String path,String tablename,int i,String databasename){
		tablename = Tools.FirstUpperCase(tablename);
		StringBuffer bf = new StringBuffer();
		if(1 == i){//I
			String p = XAP.getAPService(path, 1) + "//I" + Tools.Format(tablename) + "Service.java";
			writeFile(p,org.aisino.developer.util.XFileServiceContent.getIContent(path, tablename, 1,databasename));
		}else if(2 == i){//impl
			String p = XAP.getAPService(path, 2) + "//" + Tools.Format(tablename) + "ServiceImpl.java";
			writeFile(p,org.aisino.developer.util.XFileServiceContent.getIContent(path, tablename, 2,databasename));
		}
	}
	
	
	/**
	 * 在指定路径下创建Controller文件
	 * @param path
	 * @param n
	 */
	public static void createControllerFile(String path,String n){
		if(n != null && path != null){
			path = path + "//" + Tools.Format(Tools.FirstUpperCase(n)) + "Controller.java";
			File f1 = new File(path);
			try {
				//f1.delete();
				f1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Controller 生成相应文件内容
	 * @param path 包名
	 * @param tablename 表明
	 */
	public static void createControllerFileContent(String path,String tablename,String databasename){
		tablename = Tools.FirstUpperCase(tablename);
		StringBuffer bf = new StringBuffer();
			String p = XAP.getAPController(path) + "//" + Tools.Format(tablename) + "Controller.java";
			writeFile(p,org.aisino.developer.util.XFileControllerContent.getIContent(path, tablename,databasename));
	}
	
	/**
	 * 在指定路径下创建VO文件
	 * @param path 包名
	 * @param tablename 要生成pojo对象的表名,使用;进行分割
	 */
	public static void createVoFile(String path,String tablename){
		DB2JavaBean d2j = new DB2JavaBean(true,tablename);
		d2j.init(path,StaticVar.OBJECTTYPE);
	}

	
	
	
	
	
	
	
	
	//-----------------------------------------以下需要修改
	/**
	 * 在指定路径下创建jsp js文件
	 * @param path
	 * @param n 表名
	 */
	public static void createJspJsFile(String path,String n){
		if(n != null && path != null){
			path = path + "//" + Tools.toLowerCase(n) + ".js";
			File f1 = new File(path);
			try {
				//f1.delete();
				f1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * jsp js 生成相应文件内容
	 * @param path 包名
	 * @param tablename 表明
	 */
	public static void createJspJsFileContent(String path,String tablename,String databasename){
		tablename = Tools.toLowerCase(tablename);
		StringBuffer bf = new StringBuffer();
			String p = XAP.getAPJspJs(path) + "//" + Tools.Format(tablename).toLowerCase() + ".js";
			writeFile(p,org.aisino.developer.util.XFileJspJsContent.getJspJsContent(path, tablename,databasename));
	}
	
	/**
	 * 在指定路径下创建jsp文件
	 * @param path
	 * @param n 表名
	 */
	public static void createJspFile(String path,String n){
		if(n != null && path != null){
			path = path + "/" + Tools.toLowerCase(n) + ".html";
			File f1 = new File(path);
			try {
				//f1.delete();
				f1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * jsp 生成相应文件内容
	 * @param path 包名
	 * @param tablename 表明
	 */
	public static void createJspFileContent(String path,String tablename,String databasename){
		tablename = Tools.toLowerCase(tablename);
		StringBuffer bf = new StringBuffer();
			String p = XAP.getAPJsp(path) + "//" + Tools.Format(tablename).toLowerCase() + ".html";
			writeFile(p,org.aisino.developer.util.XFileJspContent.getJspContent(path, tablename,databasename));
	}
	
	
	
	
	
	
	/**
	 * 写文件
	 * @param filePathAndName
	 *            含路径文件名
	 * @param fileContent
	 *            写入文件的字符串
	 */
	public static void writeFile(String filePathAndName, String fileContent) {
		try {
			File f = new File(filePathAndName);
			if (!f.exists()) {
				f.createNewFile();
			}
			// 定义编码
			OutputStreamWriter write = new OutputStreamWriter(
					new FileOutputStream(f), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
		} catch (Exception e) {
			System.out.println("写文件内容操作出错");
			e.printStackTrace();
		}
	}
	
	
	/**   
     * 追加文件：使用RandomAccessFile   
     *    
     * @param fileName 文件名   
     * @param content 追加的内容   
     */    
    public static void addSqlMapContent(String filepath, String tablename ) {
    	String t = filepath;
    	if(filepath.indexOf("#") > -1){
			String ts[] = filepath.split("#");
			t = ts[1];
		}
    	String content = "<sqlMap resource= \""+t.replace(".","/")+"/dao/xml/"+tablename.substring(0, 1).toUpperCase()
		+ tablename.substring(1).toLowerCase()+"Dao.xml\"/>";
    	XaddFileContent(XAP.getSqlMapAP(filepath),StaticVar.SQLMAPXML,content+"\n	" + StaticVar.SQLMAPXML);
    }
    
    
    /**
     * 文件特定位置追加内容
     * @param filepath
     * @param oldstr
     * @param newstr
     */
    public static void XaddFileContent(String filepath,String oldstr,String newstr ){
    	try {
			File file = new File(filepath);
			// 建立一个file对象，参数就是你想访问文件的路径，这里我就不验证文件是否存在了
			// 查下api即可
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			List list =new ArrayList();
			//定义一个集合存放每一行的字符串
			while(true){
				String str=br.readLine();
				//读取文件当中的一行
				if(str==null) break;
				//如果读取的是空，也就是文件读取结束 跳出循环
				int index=str.indexOf(oldstr);
				//看此行的是否包含zhidao
				if(index!=-1){
					str = str.replace(oldstr, newstr);
					//如果包含就把zhidao 换成answer
				}
				list.add(str);
				//把修改之后的str放到集合当中
				
			}
			br.close();
			
			PrintWriter pw=new PrintWriter(file);
			//建立一个输出流，把东西写入文件
			for(int i=0;i<list.size();i++){
				String str =(String)list.get(i);
				//从集合当中取出字符串
				pw.println(str);
				//把该字符串写入文件当中
			}
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
