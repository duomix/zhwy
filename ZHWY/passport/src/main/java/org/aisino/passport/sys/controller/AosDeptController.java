package org.aisino.passport.sys.controller;

import java.util.List;
import java.util.Map;
import org.aisino.utils.common.DateUtils;
import org.aisino.utils.common.MsgMap;
import org.aisino.passport.sys.common.XConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import org.aisino.passport.sys.service.IAosDeptService;
import org.aisino.passport.sys.service.IAosUserDutyService;
import org.aisino.passport.sys.vo.AosDept;
import org.aisino.passport.sys.vo.AosDuty;

/**
 * 系统组织机构控制器
 * @author XZY
 * 2017-2-9-上午11:52:29
 */
@RestController
@RequestMapping({"/aosdept"})
public class AosDeptController {

	@Autowired
	private IAosDeptService xservice;
	@Autowired
	private IAosUserDutyService iAosUserDutyService;
	
	
	/**
	 * 查询组织机构树
	 * key     用户id
	 * keyduty  用户当前职务id
	 * pid   组织机构树pid
	 * deptname  组织机构名称（用于模糊查询）
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({"/querysystemdept.do"})
	public List<Map> querySystemRes(AosDept bean){
		//List<Map> dl = null;
		//AosDuty d = iAosUserDutyService.getUserDutyMainFormSession();
		//bean.setDeptid(d.getDeptid());
		//dl = xservice.querySystemDept(bean);
		return xservice.querySystemDept(bean);
	}
	

	@RequestMapping({"/findlist.do"})
	public Map<String,Object> findList(AosDept bean){
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		PageInfo<AosDept> data = xservice.findPage(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, data);
		return mm;
	}

	@RequestMapping({"/get.do"})
	public Map<String,Object> get(AosDept bean){
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		bean = xservice.get(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, bean);
		return mm;
	}

	@RequestMapping({"/add.do"})
	public Map<String,Object> add(AosDept bean){
		Map<String,Object> mm = null;
		String pid = bean.getPid();
		if(xservice.isExistDuty(pid) > 0){
			mm = MsgMap.getMsg(XConstants.DEPT_EXIST_DUTY_CODE,XConstants.DEPT_EXIST_DUTY, null);
		}else{
			bean.setCreateby(bean.getKey());
			bean.setCreatedate(DateUtils.getFormatDate_DT());
			Integer c = xservice.add(bean);
			if(c > 0){
				mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
			}else{
				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
			}
		}
		return mm;
	}

	@RequestMapping({"/update.do"})
	public Map<String,Object> update(AosDept bean){
		Map<String,Object> mm = null;
		String pid = bean.getPid();
		if(xservice.isExistDuty(pid) > 0){
			mm = MsgMap.getMsg(XConstants.DEPT_EXIST_DUTY_CODE,XConstants.DEPT_EXIST_DUTY, null);
		}else{
			Integer c = xservice.update(bean);
			if(c > 0){
				mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
			}else{
				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
			}
		}
		return mm;
	}
	@RequestMapping({"/delete.do"})
	public Map<String,Object> delete(AosDept bean){
		Map<String,Object> mm = null;
		Integer c  = 0;
		String[] ids = null;
		String id = bean.getDeptid();//主键
		if(id != null && !"".equals(id)){
			if(id.indexOf(",") > -1){
				ids = id.split(",");
			}else{
				ids = new String[1];
				ids[0] = id;
			}
			for(int i=0;i<ids.length;i++){
				c += xservice.del(ids[i]);
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
	
	/**
	 * 根据组织结构id判断 当前组织机构下是否存在职务
	 * @param deptid 组织机构id
	 * @return   1:存在   0:不存在
	 */
	@RequestMapping({"/isexistduty.do"})
	public Map<String,Object> isExistDuty(String deptid){
		Map<String,Object> mm = null;
		if(deptid != null && !"".equals(deptid)){
			Integer c = xservice.isExistDuty(deptid);
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