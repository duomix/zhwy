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
import org.aisino.passport.sys.service.IAosRoleResService;
import org.aisino.passport.sys.service.IAosUserDutyService;
import org.aisino.passport.sys.vo.AosDuty;
import org.aisino.passport.sys.vo.AosRoleRes;

/**
 * 角色资源控制器
 * @author XZY
 * 2017-2-9-上午11:54:04
 */
@RestController
@RequestMapping({"/aosroleres"})
public class AosRoleResController {

	@Autowired
	private IAosRoleResService xservice;
	@Autowired
	private IAosUserDutyService iAosUserDutyService;
	
	/**
	 * 查询当前人员职务角色资源并选中角色资源
	 * @param pid  资源pid
	 * @param roleid 角色id
	 * @param key 当前登陆人id
	 * @return
	 */
	@RequestMapping({"/queryrolerestree.do"})
	public List<Map> queryRoleResTree(String pid, String roleid, String key){
		List<Map> dl = null;
		AosDuty d = iAosUserDutyService.getUserDutyMainFormSession();
		dl = xservice.queryRoleResTree(pid, key, roleid, d.getDutyid());
		return dl;
	}
	
	@RequestMapping("/queryroleres.do")
	public List<Map>  queryRoleRes(String roleid,String key){
		return  xservice.queryRoleRes(roleid,key);
	}

	@RequestMapping({"/findlist.do"})
	public Map<String,Object> findList(AosRoleRes bean){
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		PageInfo<AosRoleRes> data = xservice.findPage(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, data);
		return mm;
	}

	@RequestMapping({"/get.do"})
	public Map<String,Object> get(AosRoleRes bean){
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		bean = xservice.get(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, bean);
		return mm;
	}

	@RequestMapping({"/add.do"})
	public Map<String,Object> add(AosRoleRes bean){
		Map<String,Object> mm = null;
		bean.setCreateby(bean.getKey());
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
	public Map<String,Object> update(AosRoleRes bean){
		return add(bean);
	}
	@RequestMapping({"/delete.do"})
	public Map<String,Object> delete(AosRoleRes bean){
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