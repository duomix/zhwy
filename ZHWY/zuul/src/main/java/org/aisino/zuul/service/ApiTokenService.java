package org.aisino.zuul.service;

public interface ApiTokenService {
	/**
	 * 检验token是否合法，同时验证用户是否有权访问资源
	 * @param token   访问令牌
	 * @param uri        请求的资源
	 * @return
	 */
	public  boolean   checkToken(String  token,String uri);
	
	/**
	 * 检测当前sessionid 回话是否存在
	 * @param sessionid
	 * @return  true:存在(登陆状态)   false:不存在(下线状态)
	 */
	public boolean checkUserLogin(String sessionid);
	
	/**
	 * 记录用户行为
	 * @描述:
	 * @方法名: recordBehavior
	 * @param interface_name 访问接口名
	 * @param interface_req_parm 访问参数
	 * @返回类型 void
	 * @创建人 XZY
	 * @创建时间 2017-7-3下午2:00:43
	 * @修改人 XZY
	 * @修改时间 2017-7-3下午2:00:43
	 * @修改备注
	 * @since
	 * @throws
	 */
	public void recordBehavior(String interface_name, String interface_req_parm);
}
