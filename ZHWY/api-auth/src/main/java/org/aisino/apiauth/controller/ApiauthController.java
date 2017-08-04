package org.aisino.apiauth.controller;

import java.util.Map;

import org.aisino.apiauth.common.MsgMap;
import org.aisino.apiauth.service.ApiUserService;
import org.aisino.apiauth.vo.ApiUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiauthController {
	
	@Autowired
	ApiUserService  userService;  
	
	@RequestMapping("/apiuser/{account}")
	public   Map<String,Object>  queryUser(@PathVariable String account){
			if(StringUtils.isEmpty(account)){
				return  MsgMap.getFailMsg("不合法用户");
			}
			
			ApiUser  user = userService.getUserByAccount(account);
			if(user==null){
				return  MsgMap.getFailMsg("不合法用户");
			}else{
				return  MsgMap.getSuccessMsg("", user);
			}
	}
	
	@RequestMapping("delcache")
	public  Map<String,Object>  removeCache(){
		userService.delcache();
		return MsgMap.getSuccessMsg("", null);
	}
}
