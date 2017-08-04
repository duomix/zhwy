package org.aisino.core.redis;

import org.aisino.utils.common.JsonUtil;
import org.aisino.utils.common.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Shrio Session 工具了
 * 
 * @类描述：
 * @项目名称：sys-web
 * @包名： org.aisino.shiro.common.utils
 * @类名称：SessionUtils
 * @创建人：XZY
 * @创建时间：2016-2-22上午9:30:35
 * @修改人：XZY
 * @修改时间：2016-2-22上午9:30:35
 * @修改备注：
 * @version v1.0
 */
@Component
public class SessionUtils {
	public final static String USERINFO = "admin_user";
	//管理端用户 session超时时间 单位秒
	long sessiontimeout = 60 * 30;
	
	@Autowired
	private RedisUtils redisutils;

	/**
	 * 获取 Session 中 用户信息(后台管理端用户信息)
	 * 
	 * @描述:
	 * @方法名: GetUserinfo
	 * @return
	 * @返回类型 User
	 * @创建人 XZY
	 * @创建时间 2016-2-22上午9:31:09
	 * @修改人 XZY
	 * @修改时间 2016-2-22上午9:31:09
	 * @修改备注
	 * @since
	 * @throws
	 */
	public String getUserinfo(String key) {
		String userinfo = null;
		Object obj = redisutils.getHashToValue(USERINFO, key);
		if (obj != null) {
			userinfo = PasswordUtils.passBasedDes(obj.toString());
		}
		return userinfo;
	}
	/**
	 * 将用户数据存储到session(后台管理端用户信息)
	 * 
	 * @param user
	 */
	public void putUserInfo(String key, Object user) {
		if (key != null && !"".equals(key) && user != null) {
			redisutils.setHashKeyTimeOut(USERINFO, sessiontimeout);
			redisutils.setHashToValue(USERINFO, key, PasswordUtils.passEncrypt(JsonUtil.object2json(user)));
		}
	}
	/**
	 * 删除redis 中用户信息
	 * 
	 * @param key
	 */
	public void delUserInfo(String key) {
		if (key != null && !"".equals(key)) {
			String[] keys = { key };
			redisutils.delForHash(USERINFO,keys);
		}
	}
	
}
