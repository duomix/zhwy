package org.aisino.passport.sys.controller;

import java.util.List;
import java.util.Map;
import org.aisino.utils.common.DateUtils;
import org.aisino.utils.common.MsgMap;
import org.aisino.utils.common.PasswordUtils;
import org.aisino.core.redis.SessionUtils;
import org.aisino.passport.sys.common.XConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.aisino.passport.sys.service.IAosUserService;
import org.aisino.passport.sys.vo.AosUser;
import com.github.pagehelper.PageInfo;

/**
 * 人员控制器
 * @author XZY
 * 2017-1-20-上午11:45:21
 */
@RestController
@RequestMapping({"/aosuser"})
public class AosUserController {

	@Autowired
	private IAosUserService xservice;
	@Autowired
	private SessionUtils sessionUtils;
	
	/**
	 * 根据用户查询用户可使用系统
	 * @param userid
	 * @return
	 */
	@RequestMapping({"/userressystem.do"})
	public Map<String,Object> queryUserResSystem(String userid){
		List datalist = null;
		Map<String,Object> mm = null;
		if(userid != null && !"".equals(userid)){
			datalist  = xservice.queryUserResSystem(userid);
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, datalist);
		}else{
			mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR + XConstants.PARAM_ERROR, null);
		}
		return mm;
	}
	
	
	/**
	 * 重置密码
	 * @param userid
	 * @return
	 */
	@RequestMapping({"/repa.do"})
	public Map<String,Object> resetPasssword(String userid){
		Map<String,Object> mm = null;
		//根据userid查询用户信息
		AosUser user = new AosUser();
		user.setUserid(userid);
		user = xservice.get(user);
		//设置默认密码
		AosUser tmp_user = new AosUser();
		tmp_user.setUserid(userid);
		tmp_user.setPassword(PasswordUtils.MD5Encrypt(user.getAccount(), XConstants.USER_DEFAULT_PASSWORD));
		Integer c = xservice.update(tmp_user);
		if(c > 0){
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
		}else{
			mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
		}
		return mm;
	}
	
	
	/**
	 * 用户修改密码
	 * @param key userid用户id
	 * @param old_password 原始密码
	 * @param new_password 新密码
	 * @return
	 */
	@RequestMapping({"/upps.do"})
	public Map<String,Object> updatePassword(String key, String old_password, String new_password){
		Map<String,Object> mm = null;
		AosUser user = new AosUser();
		user.setUserid(key);
		user = xservice.get(user);
		Integer c = xservice.updatePassword(user, old_password, new_password);
		if(c > 0){
			//同步修改session 中user对象password
			user.setPassword(PasswordUtils.MD5Encrypt(user.getAccount(), new_password));
			//用户信息存放redis
			sessionUtils.putUserInfo(user.getUserid(), user);
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
		}else if(c == -10){
			mm = MsgMap.getMsg(XConstants.UPDATE_PASSWORD_OLD__ERROR_CODE,XConstants.UPDATE_PASSWORD_OLD__ERROR, c);
		}else{
			mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
		}
		return mm;
	}
	
	
	

	@RequestMapping({"/findlist.do"})
	//@RequiresPermissions("userInfo:findlist")//权限管理;
	public Map<String,Object> findList(AosUser bean){
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		PageInfo<AosUser> data = xservice.findPage(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, data);
		return mm;
	}

	@RequestMapping({"/get.do"})
	public Map<String,Object> get(AosUser bean){
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		bean = xservice.get(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, bean);
		return mm;
	}
	
	/**
	 * 新增用户信息，新增用户职务信息
	 * @param bean
	 * @return
	 */
	@RequestMapping({"/add.do"})
	public Map<String,Object> add(AosUser bean){
		Map<String,Object> mm = null;
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		bean.setCreateby(bean.getKey());
		bean.setCreatedate(DateUtils.getFormatDate_DT());
		Integer c = xservice.add(bean);
		if(c > 0){
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
		}else if(c == XConstants.SYSTEM_ACCOUNT_EXIST_ERROR_CODE){
			mm = MsgMap.getMsg(XConstants.SYSTEM_ACCOUNT_EXIST_ERROR_CODE,XConstants.SYSTEM_ACCOUNT_EXIST_ERROR, c);
		}else{
				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
		}
		return mm;
	}
	
	
	@RequestMapping({"/update.do"})
	public Map<String,Object> update(AosUser bean){
		Map<String,Object> mm = null;
		Integer c = xservice.updateInfo(bean);
		if(c > 0){
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
		}else{
				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
		}
		return mm;
	}
	
	@RequestMapping({"/delete.do"})
	public Map<String,Object> delete(AosUser bean){
		Map<String,Object> mm = null;
		Integer c  = 0;
		String[] ids = null;
		String id = bean.getUserid();//主键
		if(id != null && !"".equals(id)){
			if(id.indexOf(",") > -1){
				ids = id.split(",");
			}else{
				ids = new String[1];
				ids[0] = id;
			}
			for(int i=0;i<ids.length;i++){
				c += xservice.delete(ids[i]);
			}
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

}