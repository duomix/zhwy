package org.aisino.zuul.service.impl;

import java.util.List;
import java.util.Map;

import org.aisino.zuul.common.ApiMap;
import org.aisino.zuul.common.HMACUtil;
import org.aisino.zuul.common.TokenUtil;
import org.aisino.zuul.service.ApiTokenService;
import org.aisino.zuul.service.ApiUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.sun.jersey.core.util.Base64;

@Service
public class ApiTokenServiceImpl implements ApiTokenService{
		
		private  final  static  Logger log = LoggerFactory.getLogger(ApiTokenService.class);
		
		@Autowired
		ApiUserService service;
		
		@Autowired
		RestTemplate restTemplate;

		@SuppressWarnings("unchecked")
		@Override
		public boolean checkToken(String token,String uri) {
			
			//token不能为空
			if(StringUtils.isEmpty(token)){
				log.warn("Token is empty!");
				return false;
			}
			
			//token必须符合标准格式，  account:time:hmac
			String[]  strs = token.split(":");
			if(strs.length!=3){
				log.warn("Token is invalid!");
				return false;
			}
			
			//token以服务器时间为基准，前后5分钟内有效
			if(!TokenUtil.isInTime(strs[1])){
				log.warn("token:{} 已超时",token);
				return false;
			}
			
			try {
						//token中声明的account必须是合法的用户
						Map<String,Object> res = service.queryUser(strs[0]);
						if(! (0==(Integer)res.get("code"))){
							log.warn("{} is a invalid user!",strs[0]);
							return false;
						}
						
						Map<String,Object>  user = (Map<String, Object>) res.get("data");
						String  key = user.get("password").toString();
						String   hmac = "";
						hmac = HMACUtil.encode(strs[0]+strs[1],key);
						log.debug("account:{}  time:{}  hmac: {}",strs[0],strs[1],strs[2]);
						log.debug("摘要比较，客户端：{}   服务器端：{}",strs[2],hmac);
						
						//检验token中的信息摘要是否合法
						if(!hmac.equals(strs[2])){
							log.warn("不合法的用户，客户端account：{}  token:{}",strs[0],strs[2]);
							return false;
						}
						
						//检验用户是否拥有资源的访问权限
						List<Map<String,String>> apis = (List<Map<String, String>>) user.get("apis");
						for(Map<String,String> api:apis){
							String  apiurl = api.get("apiurl");
							if(uri.startsWith(apiurl)){
								return  true;
							}
						}
						log.warn("{} 无权访问 {}",strs[0],uri);
				} catch (Exception e) {
					log.warn("摘要计算异常",e);
			}
			return false;
		}
		
		/**
		 * 检测当前sessionid 回话是否存在
		 * @param sessionid
		 * @return  true:存在(登陆状态)   false:不存在(下线状态)
		 */
		public boolean checkUserLogin(String sessionid){
			boolean b = true;
			if(sessionid != null && !"".equals(sessionid) && sessionid.indexOf("_") > -1){
				Map map = restTemplate.getForObject(ApiMap.WHCRJ_APP_USER + "?sessionid=" + sessionid, Map.class);
				System.out.println("sessionid查询用户登录信息：" + map);
				if(map != null && map.get("code") != null){
					if(!"-8".equals(map.get("code").toString())){
						b = false;
					}
				}
			}
			return b;
		}
		
		
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
		public void recordBehavior(String interface_name, String interface_req_parm){
			interface_req_parm = new String(Base64.encode(interface_req_parm));
			Map map = restTemplate.getForObject(ApiMap.WHCRJ_APP_USER_BEHAVIOR + "/add.do?interface_name=" + interface_name + "&interface_req_parm=" + interface_req_parm, Map.class);
		}
			
}
