package org.aisino.developer.util;

import java.util.HashMap;
import java.util.Map;


public class XMain {
	
	/**
	 * 
	 * @param t  项目包路径
	 * @param tablename  表明
	 * @param map  其他参数
	 */
	public static void Xinit(String t ,String tablename,Map map,String databasename){
		//工程绝对路径  D:\\GC\\SMI_V2.0\\
		String path = map.get("path").toString();//项目源代码绝对路径
		/**
		 * 第一步 创建目录
		 */
		/*System.out.println(XAP.getAP());
		System.out.println(XAP.getAPPackage(t));
		System.out.println(XAP.getAPDao(t,1));
		System.out.println(XAP.getAPDao(t,2));
		System.out.println(XAP.getAPDao(t,3));
		System.out.println(XAP.getAPService(t,1));
		System.out.println(XAP.getAPService(t,2));
		System.out.println(XAP.getAPController(t));
		System.out.println(XAP.getAPVo(t));
		System.out.println(XAP.getAPJspJs(path));
		System.out.println(XAP.getAPJsp(path));*/
		
		XAP.initMkdirAP(t,map);
		
		
		/**
		 * 第二步 创建文件并添加内容
		 */
		//dao
		XFile.createDaoFile(XAP.getAPDao(t, 1),tablename,1);
		XFile.createDaoFileContent(t,tablename,1,map,databasename);
		//XFile.createDaoFile(XAP.getAPDao(t, 2),tablename,2);
		//XFile.createDaoFileContent(t,tablename,2,map);
		XFile.createDaoFile(XAP.getAPDao(t, 3),tablename,3);
		XFile.createDaoFileContent(t,tablename,3,map,databasename);
		//service
		XFile.createServiceFile(XAP.getAPService(t, 1),tablename,1);
		XFile.createServiceFileContent(t,tablename,1,databasename);
		XFile.createServiceFile(XAP.getAPService(t, 2),tablename,2);
		XFile.createServiceFileContent(t,tablename,2,databasename);
		//controller
		XFile.createControllerFile(XAP.getAPController(t),tablename);
		XFile.createControllerFileContent(t, tablename,databasename);
		//vo  创建完整vo
		XFile.createVoFile(t,tablename);
		
		
		//jsp js
		//XFile.createJspJsFile(XAP.getAPJspJs(path), Tools.Format(tablename));
		//XFile.createJspJsFileContent(path, tablename, databasename);
		//jsp
		//XFile.createJspFile(XAP.getAPJsp(path), Tools.Format(tablename));
		//XFile.createJspFileContent(path, tablename, databasename);
		
		/**
		 * 第三步 添加SqlMap 文件 于 SqlMapConfig.xml中
		 */
		//XFile.addSqlMapContent(t,tablename);
	}
	
	
	/*public static void main(String[] args) {
		String t = "org.aisino.aos.dev";
		String tablename = "dev_hand";
		String databasename = "aosx";
		Map m = new HashMap();
		m.put("path", "/home/yzx/GC/eclipse/aosx_easyui/");
		Xinit(t,tablename,m,databasename);
		System.out.println("完成......");
	}*/
}
