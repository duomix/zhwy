package org.aisino.passport.sys.controller;

import java.util.List;
import java.util.Map;
import org.aisino.utils.common.MsgMap;
import org.aisino.utils.common.UUID;
import org.aisino.passport.sys.common.XConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.aisino.passport.sys.service.IAosConstantsService;
import org.aisino.passport.sys.vo.AosConstants;
import com.github.pagehelper.PageInfo;

/**
 * 常量控制器
 * @author XZY
 * 2017-1-20-上午11:43:19
 */
@RestController
@RequestMapping({"/aosconstants"})
public class AosConstantsController {

	@Autowired
	private IAosConstantsService xservice;
	
	
	/**
	 * 查询字典树
	 * @param bean
	 * @return
	 */
	@RequestMapping({"/querysystemcon.do"})
	public List<Map> querySystemRes(AosConstants bean){
		List<Map> dl = null;
		dl = xservice.querySystemCon(bean);
		return dl;
	}
	

	@RequestMapping({"/findlist.do"})
	public Map<String,Object> findList(AosConstants bean){
		PageInfo<AosConstants> data = xservice.findPage(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, data);
		return mm;
	}

	@RequestMapping({"/get.do"})
	public Map<String,Object> get(AosConstants bean){
		bean = xservice.get(bean);
		Map<String,Object> mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,null, bean);
		return mm;
	}

	@RequestMapping({"/add.do"})
	public Map<String,Object> add(AosConstants bean){
		Map<String,Object> mm = null;
		bean.setId(UUID.GetUUID32());//设置主键
		Integer c = xservice.insert(bean);
		if(c > 0){
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
		}else{
				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
		}
		return mm;
	}

	@RequestMapping({"/update.do"})
	public Map<String,Object> update(AosConstants bean){
		Map<String,Object> mm = null;
		String type = bean.getType();
		/*if(type != null && type.indexOf(XConstants.CONSTANTS_IMAGE_TYPE) > -1){
			bean.setValue(XConstants.CONSTANTS_IMAGE_VALUE_PREFIX + bean.getValue());
		}*/
		Integer c = xservice.update(bean);
		if(c > 0){
			mm = MsgMap.getMsg(XConstants.MSG_SUCCES_CODE,XConstants.MSG_SUCCES, c);
		}else{
				mm = MsgMap.getMsg(XConstants.MSG_ERROR_CODE,XConstants.MSG_ERROR, c);
		}
		return mm;
	}
	@RequestMapping({"/delete.do"})
	public Map<String,Object> delete(AosConstants bean){
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
	
	
	/**
	 * 查询系统登录页面
	 * @return
	 */
	@RequestMapping({"/slp.do"})
	public Map<String,Object> update(){
		return xservice.querySystemLoginPage();
	}

}