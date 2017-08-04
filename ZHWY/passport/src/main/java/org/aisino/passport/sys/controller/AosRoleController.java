package org.aisino.passport.sys.controller;

import java.util.Map;
import org.aisino.utils.common.MsgMap;
import org.aisino.utils.common.UUID;
import org.aisino.passport.sys.common.XConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import org.aisino.passport.sys.service.IAosRoleService;
import org.aisino.passport.sys.vo.AosRole;

/**
 * 角色控制器
 * @author XZY
 * 2017-2-9-上午11:53:54
 */
@RestController
@RequestMapping({"/aosrole"})
public class AosRoleController {

	@Autowired
	private IAosRoleService xservice;
	
	/**
	 * 查询当前用户角色
	 * @param bean
	 * @return
	 */
	@RequestMapping({"/findlist.do"})
	public Map<String,Object> findList(AosRole bean){
		bean.setCreateby(bean.getKey());//创建人为当前登录人
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		PageInfo<AosRole> data = xservice.findPage(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, data);
		return mm;
	}

	@RequestMapping({"/get.do"})
	public Map<String,Object> get(AosRole bean){
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		bean = xservice.get(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, bean);
		return mm;
	}

	@RequestMapping({"/add.do"})
	public Map<String,Object> add(AosRole bean){
		Map<String,Object> mm = null;
		bean.setCreateby(bean.getKey());
		String uuid = UUID.GetUUID32();
		bean.setRoleid(uuid);//设置主键
		Integer  c = xservice.add(bean);
		if(c > 0){
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, uuid);
		}else if(c == XConstants.SYSTEM_ROLE_EXIST_ERROR_CODE){
			mm = MsgMap.getMsg(XConstants.SYSTEM_ROLE_EXIST_ERROR_CODE,XConstants.SYSTEM_ROLE_EXIST_ERROR, c);
		}else{
			mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
		}
		return mm;
	}

	@RequestMapping({"/update.do"})
	public Map<String,Object> update(AosRole bean){
		Map<String,Object> mm = null;
		Integer c = xservice.updateinfo(bean);
		if(c > 0){
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
		}else{
				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
		}
		return mm;
	}
	@RequestMapping({"/delete.do"})
	public Map<String,Object> delete(AosRole bean){
		Map<String,Object> mm = null;
		Integer c  = 0;
		String[] ids = null;
		String id = bean.getRoleid();//主键
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