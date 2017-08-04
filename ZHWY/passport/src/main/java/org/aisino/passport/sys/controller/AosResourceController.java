package org.aisino.passport.sys.controller;

import java.util.List;
import java.util.Map;
import org.aisino.utils.common.MsgMap;
import org.aisino.passport.sys.common.XConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import org.aisino.passport.sys.service.IAosResourceService;
import org.aisino.passport.sys.vo.AosResource;

/**
 * 资源控制器
 * @author XZY
 * 2017-2-9-上午11:53:43
 */
@RestController
@RequestMapping({"/aosresource"})
public class AosResourceController {

	@Autowired
	private IAosResourceService xservice;
	
	
	/**
	 * 查询系统用户菜单
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({"/queryuserres.do"})
	public List<Map> queryUserRes(String respid, String userid){
		List<Map> dl = null;
		dl = xservice.queryUserRes(respid,userid);
		return dl;
	}
	
	
	/**
	 * 查询系统用户资源
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({"/querysystemres.do"})
	public List<Map> querySystemRes(AosResource bean){
		List<Map> dl = null;
		dl = xservice.querySystemRes(bean);
		return dl;
	}
	
	
	

	@RequestMapping({"/findlist.do"})
	public Map<String,Object> findList(AosResource bean){
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		PageInfo<AosResource> data = xservice.findPage(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, data);
		return mm;
	}

	@RequestMapping({"/get.do"})
	public Map<String,Object> get(AosResource bean){
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		bean = xservice.get(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, bean);
		return mm;
	}

	@RequestMapping({"/add.do"})
	public Map<String,Object> add(AosResource bean){
		Map<String,Object> mm = null;
		Integer c = xservice.add(bean);
		if(c > 0){
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
		}else if(c == XConstants.SYSTEM_RESOURCE_EXIST_ERROR_CODE){
			mm = MsgMap.getMsg(XConstants.SYSTEM_RESOURCE_EXIST_ERROR_CODE,XConstants.SYSTEM_RESOURCE_EXIST_ERROR, c);
		}else{
			mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
		}
		return mm;
	}

	@RequestMapping({"/update.do"})
	public Map<String,Object> update(AosResource bean){
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
	public Map<String,Object> delete(AosResource bean){
		Map<String,Object> mm = null;
		Integer c  = 0;
		String[] ids = null;
		String id = bean.getResid();//主键
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