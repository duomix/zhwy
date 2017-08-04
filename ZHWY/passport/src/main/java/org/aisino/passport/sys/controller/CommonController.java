package org.aisino.passport.sys.controller;

import java.util.HashMap;
import java.util.Map;
import org.aisino.passport.sys.common.XConstants;
import org.aisino.passport.sys.service.IAosConstantsService;
import org.aisino.passport.sys.vo.AosConstants;
import org.aisino.utils.common.MsgMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共控制器
 * @author root
 *
 */
@RestController
public class CommonController {
	
	@Autowired
	IAosConstantsService iAosConstantsService;
	
	/**
	 * 查询版权信息
	 * @return
	 */
	@RequestMapping({"/querycopyright.do"})
	public Map<String,Object> querySystemCopyRight(){
		Map<String,Object> mm = null;
		AosConstants ac = new AosConstants();
		ac.setType(XConstants.SYSTEM_COPYRIGHT);
		ac = iAosConstantsService.get(ac);
		mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, ac.getCode());
		return mm;
	}
	
	/**
	 * 设置版权
	 * @param bean
	 * @return
	 */
	@RequestMapping({"/setcopyright.do"})
	public Map<String,Object> systemCopyRight(String copyright){
		Map<String,Object> mm = null;
		if(copyright != null && !"".equals(copyright)){
			AosConstants ac = new AosConstants();
			ac.setType(XConstants.SYSTEM_COPYRIGHT);
			ac = iAosConstantsService.get(ac);
			ac.setCode(copyright);
			Integer c = iAosConstantsService.update(ac);
			if(c > 0){
				mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
			}else{
					mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
			}
		}else{
			mm = MsgMap.getMsg(XConstants.PARAM_ERROR_CODE,XConstants.PARAM_ERROR, null);
		}
		return mm;
	}
	
	
	/**
	 * 查询管理首页样式
	 * @return
	 */
	@RequestMapping({"/queryadminhomestyle.do"})
	public Map<String,Object> queryAdminHomeStyle(){
		Map<String,Object> mm = null;
		Map<String,String> data = new HashMap<String,String>();
		AosConstants ac = new AosConstants();
			ac.setType(XConstants.IMAGE_ADMIN_HOME_LOGO);
			ac = iAosConstantsService.get(ac);
			data.put(XConstants.IMAGE_ADMIN_HOME_LOGO, ac.getCode());
			ac = new AosConstants();
			ac.setType(XConstants.IMAGE_ADMIN_HOME_TITLE);
			ac = iAosConstantsService.get(ac);
			data.put(XConstants.IMAGE_ADMIN_HOME_TITLE, ac.getCode());
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, data);
		return mm;
	}
	/**
	 * 设置管理首页样式
	 * @param home_logo  logo图
	 * @param home_title   标题图
	 * @return
	 */
	@RequestMapping({"/setadminhomestyle.do"})
	public Map<String,Object> adminHomeStyle(String home_logo, String home_title){
		Map<String,Object> mm = null;
		Integer c = 0;
		AosConstants ac = null;
		//设置logo图
		if(home_logo != null && !"".equals(home_logo)){
			ac = new AosConstants();
			ac.setType(XConstants.IMAGE_ADMIN_HOME_LOGO);
			ac = iAosConstantsService.get(ac);
			ac.setCode(home_logo);
			c += iAosConstantsService.update(ac);
		}
		//设置标题图
		if(home_title != null && !"".equals(home_title)){
			ac = new AosConstants();
			ac.setType(XConstants.IMAGE_ADMIN_HOME_TITLE);
			ac = iAosConstantsService.get(ac);
			ac.setCode(home_title);
			c += iAosConstantsService.update(ac);
		}
		if(c > 0){
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
		}else{
				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
		}
		return mm;
	}
	
	
	
	/**
	 * 查询登陆页面样式设置
	 * @param login_style  样式名称  取值只能为  str_login_style_1或str_login_style_2
	 * @param pageimg_bak  背景图
	 * @param pageimg_logo logo图
	 * @param pageimg_title  标题图
	 * @return
	 */
	@RequestMapping({"/queryloginstyle.do"})
	public Map<String,Object> queryLoginStyle(String login_style){
		Map<String,Object> mm = null;
		Map<String,String> data = new HashMap<String,String>();
		Integer c = 0;
		AosConstants ac = null;
		ac.setType(login_style);
		ac = iAosConstantsService.get(ac);
		String pid = ac.getPid();
		
			ac = new AosConstants();
			ac.setPid(pid);
			ac.setType(XConstants.SYSTEM_LOGIN_PAGE_IMG_BAK);
			ac = iAosConstantsService.get(ac);
			data.put(XConstants.SYSTEM_LOGIN_PAGE_IMG_BAK, ac.getCode());
		
			ac = new AosConstants();
			ac.setPid(pid);
			ac.setType(XConstants.SYSTEM_LOGIN_PAGE_IMG_LOGO);
			ac = iAosConstantsService.get(ac);
			data.put(XConstants.SYSTEM_LOGIN_PAGE_IMG_LOGO, ac.getCode());
		
			ac = new AosConstants();
			ac.setPid(pid);
			ac.setType(XConstants.SYSTEM_LOGIN_PAGE_IMG_TITLE);
			ac = iAosConstantsService.get(ac);
			data.put(XConstants.SYSTEM_LOGIN_PAGE_IMG_TITLE, ac.getCode());
			
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, data);
		return mm;
	}
	
	
	/**
	 * 登陆页面样式设置
	 * @param login_style  样式名称  取值只能为  str_login_style_1或str_login_style_2
	 * @param pageimg_bak  背景图
	 * @param pageimg_logo logo图
	 * @param pageimg_title  标题图
	 * @return
	 */
	@RequestMapping({"/setloginstyle.do"})
	public Map<String,Object> LoginStyle(String login_style, String pageimg_bak, String pageimg_logo, String pageimg_title){
		Map<String,Object> mm = null;
		Integer c = 0;
		AosConstants ac = null;
		ac.setType(login_style);
		ac = iAosConstantsService.get(ac);
		String pid = ac.getPid();
		
		if(pageimg_bak != null && !"".equals(pageimg_bak)){
			//设置背景图
			ac = new AosConstants();
			ac.setPid(pid);
			ac.setType(XConstants.SYSTEM_LOGIN_PAGE_IMG_BAK);
			ac = iAosConstantsService.get(ac);
			ac.setCode(pageimg_bak);
			c += iAosConstantsService.update(ac);
		}
		
		if(pageimg_title != null && !"".equals(pageimg_title)){
			//设置logo图
			ac = new AosConstants();
			ac.setPid(pid);
			ac.setType(XConstants.SYSTEM_LOGIN_PAGE_IMG_LOGO);
			ac = iAosConstantsService.get(ac);
			ac.setCode(pageimg_title);
			c += iAosConstantsService.update(ac);
		}
		
		if(pageimg_logo != null && !"".equals(pageimg_logo)){
			//设置标题图
			ac = new AosConstants();
			ac.setPid(pid);
			ac.setType(XConstants.SYSTEM_LOGIN_PAGE_IMG_TITLE);
			ac = iAosConstantsService.get(ac);
			ac.setCode(pageimg_logo);
			c += iAosConstantsService.update(ac);
		}
		if(c > 0){
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
		}else{
				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
		}
		return mm;
	}
}
