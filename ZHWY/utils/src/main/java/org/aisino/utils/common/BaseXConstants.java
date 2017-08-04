package org.aisino.utils.common;


/**
 * web层 系统常量
 */
public class BaseXConstants {
		
	/**
	 * 操作提示
	 */
	public static final Integer MSG_SUCCES_CODE = 0;
	public static final String MSG_SUCCES = "操作成功。";//操作成功提示
	
	public static final Integer MSG_ERROR_CODE = -1;
	public static final String MSG_ERROR = "操作失败！";//操作失败提示
	
	public static final Integer PARAM_ERROR_CODE = -2;
	public static final String PARAM_ERROR = "参数不合法！";//参数不合法提示
	
	public static final Integer UPDATE_PASSWORD_OLD__ERROR_CODE = -3;
	public static final String UPDATE_PASSWORD_OLD__ERROR = "原始密码不正确！";

	public static final Integer SYSTEM_ACCOUNT_EXIST_ERROR_CODE = -4;
	public static final String SYSTEM_ACCOUNT_EXIST_ERROR = "账户名已存在！";//系统账户重复提示
	
	public static final Integer SYSTEM_RESOURCE_EXIST_ERROR_CODE = -5;
	public static final String SYSTEM_RESOURCE_EXIST_ERROR = "系统资源代码已存在！";//系统资源code重复
	
	
	public static final Integer SYSTEM_ROLE_EXIST_ERROR_CODE = -6;
	public static final String SYSTEM_ROLE_EXIST_ERROR = "系统角色代码已存在！";//系统角色code重复
	
	/**
	 * 密码MD5加密次数
	 */
	public static final int PASSWORD_MD5_COUNT = 3;
	/**
	 * 错误登陆次数锁定账户
	 */
	public static final Integer PASSWORD_LOGIN_FAULT_COUNT = 5;
	
	/**
	 * 账户锁顶时间  单位分钟
	 */
	public static final int ACCOUNT_LOCK_TIME = 1;
	
	/**
	 * 账户身份类型标识
	 */
	public static final String IDENTITYTYPE_ADMIN = "1";//管理员
	public static final String IDENTITYTYPE_BUSSINES = "2";//业务人员
	public static final String IDENTITYTYPE_DEVELOPER = "3";//开发人员
	
	/**
	 * 用户账户状态
	 */
	public static final String ACCOUNT_STATUS_NORMAL = "1";//正常
	public static final String ACCOUNT_STATUS_LOCK = "2";//锁定
	public static final String ACCOUNT_STATUS_LOGOUT = "3";//注销
	
	/**
	 * 数据是否有效标识
	 */
	public static final String DATA_AVAILABLE_YES = "1";//有效
	public static final String DATA_AVAILABLE_NO = "0";//无效
	//数据根id
	public static final String DATA_ROOT_ID = "0";
	
	
	/**
	 * 请求来源
	 */
	public static final String REQUEST_SOURCE_WEB = "web";//web来源
	public static final String REQUEST_SOURCE_APP = "app";//app来源
	public static final String REQUEST_SOURCE_WX = "wx";//微信来源
	public static final String REQUEST_SOURCE_ZFB = "zfb";//支付宝来源
	
}

