package org.aisino.passport.sys.controller;

import java.util.List;
import java.util.Map;
import org.aisino.utils.common.DateUtils;
import org.aisino.utils.common.MsgMap;
import org.aisino.utils.common.UUID;
import org.aisino.passport.sys.common.XConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import org.aisino.passport.sys.service.IAosDutyService;
import org.aisino.passport.sys.vo.AosDuty;

/**
 * 系统职务控制器
 * @author XZY
 * 2017-2-9-上午11:52:57
 */
@RestController
@RequestMapping({"/aosduty"})
public class AosDutyController {

	@Autowired
	private IAosDutyService xservice;

	@RequestMapping({"/findlist.do"})
	public Map<String,Object> findList(AosDuty bean){
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		PageInfo<AosDuty> data = xservice.findPage(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, data);
		return mm;
	}

	@RequestMapping({"/get.do"})
	public Map<String,Object> get(AosDuty bean){
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		bean = xservice.get(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, bean);
		return mm;
	}

	@RequestMapping({"/add.do"})
	public Map<String,Object> add(AosDuty bean){
		Map<String,Object> mm = null;
		bean.setDutyid(UUID.GetUUID32());//设置主键
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		bean.setCreateby(bean.getKey());
		bean.setCreatedate(DateUtils.getFormatDate_DT());
		Integer c = xservice.insert(bean);
		if(c > 0){
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
		}else{
				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
		}
		return mm;
	}

	@RequestMapping({"/update.do"})
	public Map<String,Object> update(AosDuty bean){
		Map<String,Object> mm = null;
		Integer c = xservice.update(bean);
		if(c > 0){
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
		}else{
				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
		}
		return mm;
	}
	@RequestMapping({"/delete.do"})
	public Map<String,Object> delete(AosDuty bean){
		Map<String,Object> mm = null;
		Integer c  = 0;
		String[] ids = null;
		String id = bean.getDutyid();//主键
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
	
	@RequestMapping({"/querydutytree.do"})
	public List<Map<String,Object>> querydutytree(String pid){
		List<Map<String,Object>> dutys = xservice.queryDutyTree(pid);
		return dutys;
	}
	
}