package org.aisino.developer.util;

import java.io.File;
import java.util.Map;

/**
 * 绝对路径
 * @author Administrator
 *
 */
public class XAP {
	
	/**
	 * 获取IDE 工作环境 绝对路径 D:\GC\SMI_V1.0\src
	 * @param javaOrresource   maven工程   true:java目录   false:resoures目录
	 * @return
	 */
	public static String getAP(Boolean javaOrresource){
		//获取当前工程路径 适用main方法调用
		String x = System.getProperty("user.dir").replace("/", "/") + "/src/";
		if(javaOrresource != null && javaOrresource.booleanValue()){
			x +=  "main/java/";//main/java/
		}else{
			x += "main/resources/";//mian/resources/
		}
		return x;
	}
	
	/**
	 * 获取指定包 绝对路径 如 com.aisino.developer --> D:\GC\SMI_V1.0\src\com\aisino\developer
	 * @param t
	 * @return
	 */
	public static String getAPPackage(String t){
		String x = getAP(true);
		String s = null;
		if(null != t){
			if(t.indexOf("#") > -1){
				String ts[] = t.split("#");
				x = ts[0] + "/src/";
				t = ts[1];
			}
			if(t.indexOf(".") > -1){
				t = t.replace(".", "/");
				s = x + t;
			}
		}
		return s;
	}
	
	/**
	 * 获取指定包  dao接口与实现包 绝对路径 
	 * @param t 
	 * @param i 1：接口   2：impl实现   其他:xml
	 * @return
	 */
	public static String getAPDao(String t, int i){
		String x = getAP(true);
		String s = null;
		if(null != t){
			if(t.indexOf("#") > -1){
				String ts[] = t.split("#");
				x = ts[0] + "/src/";
				t = ts[1];
			}
			if(t.indexOf(".") > -1){
				t = t.replace(".", "/");
				if(1 == i){
					s = x + t + "/dao";
				}else if(2 == i){
					s = x + t + "/dao/impl";
				}else{
					x = getAP(false);
					String temp = t.substring(t.lastIndexOf("/"), t.length());
					s = x + "mapper" + temp;
				}
			}
		}
		return s;
	}
	/**
	 * 获取dao 接口 包路径
	 * @param t
	 * @return
	 */
	public static String getAPIDaoBackage(String t){
		String s = null;
		if(null != t){
			if(t.indexOf(".") > -1){
				s = t + ".dao.";
			}
		}
		return s;
	}
	
	
	/**
	 * 获取指定包  Service接口与实现包 绝对路径 
	 * @param t 
	 * @param i 1：接口   2：impl实现
	 * @return
	 */
	public static String getAPService(String t, int i){
		String x = getAP(true);
		String s = null;
		if(null != t){
			if(t.indexOf("#") > -1){
				String ts[] = t.split("#");
				x = ts[0] + "/src/";
				t = ts[1];
			}
			if(t.indexOf(".") > -1){
				t = t.replace(".", "/");
				if(1 == i){
					s = x + t + "/service";
				}else if(2 == i){
					s = x + t + "/service/impl";
				}
			}
		}
		return s;
	}
	
	/**
	 * 获取指定包  controller包 绝对路径 
	 * @param t 
	 * @return
	 */
	public static String getAPController(String t){
		String x = getAP(true);
		String s = null;
		if(null != t){
			if(t.indexOf("#") > -1){
				String ts[] = t.split("#");
				x = ts[0] + "/src/";
				t = ts[1];
			}
			if(t.indexOf(".") > -1){
				t = t.replace(".", "/");
				s = x + t + "/controller";
			}
		}
		return s;
	}
	
	/**
	 * 获取指定包  vo包 绝对路径 
	 * @param t 
	 * @return
	 */
	public static String getAPVo(String t){
		String x = getAP(true);
		String s = null;
		if(null != t){
			if(t.indexOf("#") > -1){
				String ts[] = t.split("#");
				x = ts[0] + "/src/";
				t = ts[1];
			}
			if(t.indexOf(".") > -1){
				t = t.replace(".", "/");
				s = x + t + "/vo";
			}
		}
		return s;
	}
	
	/**
	 * 检查给定包路径是否存在 如 com.aisino.developer.yzx 不存在则创建
	 * @param t
	 * @return
	 */
    public static boolean createMkdirAP(String t){
    	boolean b = false;
    	if(t.indexOf("#") > -1){
			String ts[] = t.split("#");
			t = ts[0] + "/src/" + ts[1].replace(".", "/");
		}else{
			t = getAP(true) + t.replace(".", "/");
		}
		File f = new File(t);
		b = f.mkdirs();
    	return b;
    }
    
    /**
     * 根据给定的路径创建文件夹
     * @param t
     * @return
     */
    public static boolean createM(String t){
    	boolean b = false;
		File f = new File(t);
		b = f.mkdirs();
    	return b;
    }
    
    
    /**
     * 获取sqlmap  xml 文件路径
     * @param t
     * @return
     */
    public static String getSqlMapAP(String filepath){
    	//获取当前工程路径
		String x = getAP(true);
		if(filepath.indexOf("#") > -1){
			String ts[] = filepath.split("#");
			x = ts[0] + "/src/";
		}
		x = x + "SqlMapConfig.xml";
		return x;
    }
    
    
    /**
     * 获取jsp  js 文件路径
     * @param path 工程绝对路径
     * @return
     */
    public static String getAPJspJs(String path){
    	//获取当前工程路径
		String x = path;
		x = x + "src/main/resources/resources/js";
		return x;
    }
    
    /**
     * 获取jsp 文件路径
     * @param path 工程绝对路径
     * @return
     */
    public static String getAPJsp(String path){
    	//获取当前工程路径
		String x = path;
		x = x + "src/main/resources/templates";
		return x;
    }
    
    /**
     * 初始化包路径
     * @param t
     * @return
     */
    public static void initMkdirAP(String t,Map map){
    	//初始化主包路径
    	createMkdirAP(t);
    		//dao 路径
    		createM(getAPDao(t,1));
    		//createM(getAPDao(t,2));
    		createM(getAPDao(t,3));
    		//service 路径
    		createM(getAPService(t,1));
    		createM(getAPService(t,2));
    		//controller 路径
    		createM(getAPController(t));
    		//vo 路径
    		createM(getAPVo(t));
    		//jsp js 路径
    		//createM(getAPJspJs(map.get("path").toString()));
    		//jsp 路径
    		//createM(getAPJsp(map.get("path").toString()));
    }

	
	/*public static void main(String[] args) {
		String t = "org.aisino.aos.demo";
		*//**
		 * 第一步 创建目录
		 *//*
		initMkdirAP(t,null);
		System.out.println(getAP(true));
	}*/
}
