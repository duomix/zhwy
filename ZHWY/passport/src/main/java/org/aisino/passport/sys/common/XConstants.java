package org.aisino.passport.sys.common;

import org.aisino.utils.common.BaseXConstants;


/**
 * web层 系统常量
 */
public class XConstants extends BaseXConstants{
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
	
	public static final Integer DEPT_EXIST_DUTY_CODE = -7;
	public static final String DEPT_EXIST_DUTY = "该组织机构下存在职务，请解帮职务后再新增！";//组织结构职务提示
	
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
	 * 系统版权标识符号
	 */
	public static final String SYSTEM_COPYRIGHT = "html_system_copyright";
	
	
	/**
	 * 系统登录页面标识符号
	 */
	public static final String SYSTEM_LOGIN_PAGE_TYPE = "id_system_login";
	//登录页文件名称
	public static final String SYSTEM_LOGIN_PAGE_NAME = "str_loginpagename";
	/**
	 * 登陆页样式
	 */
	public static final String LOGIN_STYLE_1 = "str_login_style_1";//样式一
	public static final String LOGIN_STYLE_2 = "str_login_style_2";//样式而二
	//登录页背景图
	public static final String SYSTEM_LOGIN_PAGE_IMG_BAK = "image_pageimg_bak";
	//登录页LOGO图
	public static final String SYSTEM_LOGIN_PAGE_IMG_LOGO = "image_pageimg_logo";
	//登录页标题图
	public static final String SYSTEM_LOGIN_PAGE_IMG_TITLE = "image_pageimg_title";
	
	//用户主职务在redis中标识
	public static final String USER_DUTY_MAIN = "user_duty_main";
	//用户职务在redis中标识
	public static final String USER_DUTY = "user_duty";
	
	
	//管理首页设置
	public static final String STR_ADMIN_HOME = "str_admin_home";
	//首页LOGO图
	public static final String IMAGE_ADMIN_HOME_LOGO = "image_admin_home_logo";
	//首页标题图
	public static final String IMAGE_ADMIN_HOME_TITLE = "image_admin_home_title";
	
	
	/**
	 * 数据节点是否为叶子节点
	 */
	public static final String LEAF_NODE_YES = "0";//是叶子节点
	public static final String LEAF_NODE_NO = "1";//不是叶子节点
	
	
	
	/**
	 * 字典项中type 包含此标识则为图片类型 value 值添加前缀
	 */
	public static final String CONSTANTS_IMAGE_TYPE = "image";
	public static final String CONSTANTS_IMAGE_VALUE_PREFIX = "download.do?key=";
	
	
	/**
	 * 用户职务类型   1:主职务     0:副职务
	 */
	public static final String DUTY_TYPE_MIAN = "1";
	public static final String DUTY_TYPE_ASSIS = "0";
	
	/**
	 * 账户默认密码
	 */
	public static final String USER_DEFAULT_PASSWORD = "111111";
}

