package org.aisino.passport.sys.controller;

import java.util.Map;
import org.aisino.utils.common.DateUtils;
import org.aisino.utils.common.MsgMap;
import org.aisino.passport.sys.common.XConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import org.aisino.passport.sys.service.IAosUserDutyService;
import org.aisino.passport.sys.vo.AosDuty;
import org.aisino.passport.sys.vo.AosUserDuty;

/**
 * 人员职务控制器
 * @author XZY
 * 2017-2-9-上午11:54:16
 */
@RestController
@RequestMapping({"/aosuserduty"})
public class AosUserDutyController {

	@Autowired
	private IAosUserDutyService xservice;
	
	
	/**
	 * 设置用户主职务
	 * @param bean
	 * @return
	 */
	@RequestMapping({"/setuserdutymain.do"})
	public Map<String,Object> setUserDutyMain(AosUserDuty bean){
		Map<String,Object> mm = null;
		Integer c = xservice.setUserDutyMain(bean.getUserid(), bean.getDutyid());
		if(c > 0){
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
		}else{
				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
		}
		return mm;
	}
	
	
	
	/**
	 * 根据职务id切换职务
	 * @param dutyid
	 * @return
	 */
	@RequestMapping({"/switcheruserduty.do"})
	public Map<String,Object> switcherUserDuty(String dutyid){
		xservice.switcherUserDuty(dutyid);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, 0);
		return mm;
	}

	/**
	 * 从session中获取当前使用职务
	 * @return
	 */
	@RequestMapping({"/getuserdutymain.do"})
	public Map<String,Object> getUserDutyMainFormSession(){
		AosDuty bean = xservice.getUserDutyMainFormSession();
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, bean);
		return mm;
	}
	

	@RequestMapping({"/findlist.do"})
	public Map<String,Object> findList(AosUserDuty bean){
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		PageInfo<AosUserDuty> data = xservice.findPage(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, data);
		return mm;
	}

	@RequestMapping({"/get.do"})
	public Map<String,Object> get(AosUserDuty bean){
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		bean = xservice.get(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, bean);
		return mm;
	}

	@RequestMapping({"/add.do"})
	public Map<String,Object> add(AosUserDuty bean){
		Map<String,Object> mm = null;
		bean.setCreateby(bean.getUserid());
		bean.setCreatedate(DateUtils.getFormatDate_DT());
		Integer c = xservice.add(bean);
		if(c > 0){
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
		}else{
				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
		}
		return mm;
	}

	@RequestMapping({"/update.do"})
	public Map<String,Object> update(AosUserDuty bean){
		return add(bean);
	}
	@RequestMapping({"/delete.do"})
	public Map<String,Object> delete(AosUserDuty bean){
		Map<String,Object> mm = null;
		Integer c  = 0;
		String[] ids = null;
		String id = bean.getId();//主键
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