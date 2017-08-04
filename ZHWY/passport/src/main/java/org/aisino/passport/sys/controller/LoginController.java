package org.aisino.passport.sys.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.aisino.utils.common.JsonUtil;
import org.aisino.core.redis.RedisUtils;
import org.aisino.core.redis.SessionUtils;
import org.aisino.passport.sys.service.IAosUserService;
import org.aisino.passport.sys.vo.AosUser;

/**
 * Login控制
 */
@RestController
public class LoginController {
	
	@Autowired
	private IAosUserService iAosUserService;
	@Autowired
	RedisUtils redisUtils;
	@Autowired
	SessionUtils sessionUtils;
	
	
	
	/**
	 * 登入验证
	 * @param ac 登录账户
	 * @param pa 登录密码明文
	 * @param k 单点登录标示暂为用户userid
	 * 临时处理办法： 传递userid 后台检测 redis中是否存在用户登录信息，有则模拟登录   后期大神重新设计改造，保重......
	 */
	@RequestMapping(value="ula")//,method=RequestMethod.POST
	public Map<String,Object>  UserLogin(String ac,String pa, String k){
		String pa_m = null;
		System.out.println("登录参数------>ac:"+ac+"  "+"pa:"+pa+"  "+"k:"+k);
		if(k != null && !"".equals(k)){
			String user_json = sessionUtils.getUserinfo(k);
			System.out.println("用户信息------>:"+user_json);
			if(user_json != null && !"".equals(user_json)){
				AosUser user = JsonUtil.JsonObjStr2JavaBean(user_json, AosUser.class);
				ac = user.getAccount();
				pa_m = user.getPassword();
				pa = pa_m;//当k不为空时 pa只做占位用无实际业务意义
			}
		}
		return  iAosUserService.doGetAuthenticationInfo(ac, pa, pa_m);
	}
	
	/**
	 * 用户登出
	 * @return
	 */
	@RequestMapping("/logout")
	public void logout(String userid){
		sessionUtils.delUserInfo(userid);
	}
	
}