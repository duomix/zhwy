package org.aisino.passport.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.aisino.core.base.BaseService;
import org.aisino.passport.sys.service.IAosConstantsService;
import org.aisino.passport.sys.service.common.ServiceUtils;
import org.aisino.passport.sys.vo.AosConstants;
import org.aisino.passport.sys.common.XConstants;
import org.aisino.passport.sys.dao.IAosConstantsDao;

/**
 * 系统字典服务
 * @author XZY
 * 2017-2-8-下午4:24:53
 */
@Service
public class AosConstantsServiceImpl  extends BaseService<AosConstants,IAosConstantsDao> implements IAosConstantsService{
	
	@Autowired
	IAosConstantsDao iAosConstantsDao;
	
	/**
	 * 查询系统登录页面
	 * @return
	 */
	public Map<String,Object> querySystemLoginPage(){
		Map<String,Object> map = new HashMap<String,Object>();
		AosConstants tmp_login = new AosConstants();
		//查询登陆页面
		tmp_login.setType(XConstants.SYSTEM_LOGIN_PAGE_TYPE);
		tmp_login = iAosConstantsDao.get(tmp_login);
		//根据tmp_login 中 code查询页面
		AosConstants tmp_style = new AosConstants();
		tmp_style.setId(tmp_login.getCode());
		
		tmp_style = iAosConstantsDao.get(tmp_style);
		AosConstants tmp_style_img = new AosConstants();
		tmp_style_img.setPid(tmp_style.getId());
		List<AosConstants> dl = iAosConstantsDao.findList(tmp_style_img);
		String login_page_name = tmp_style.getCode();//登录页文件名称
		map.put(XConstants.SYSTEM_LOGIN_PAGE_NAME, login_page_name);
		if(dl != null){
			for(int i=0;i<dl.size();i++){
				AosConstants tmp = dl.get(i);
					map.put(tmp.getType(), tmp.getCode());
			}
		}
		return map;
	}
	
	/**
	 * 查询系统版权信息
	 * @return
	 */
	public String querySystemCopyRight(){
		AosConstants tmp = new AosConstants();
		tmp.setType(XConstants.SYSTEM_COPYRIGHT);
		tmp = iAosConstantsDao.get(tmp);
		if(tmp != null){
			return tmp.getCode();
		}else{
			return "";
		}
	}
	
	
	/**
	 * 查询字典树
	 * @param map
	 * @return
	 */
	public List<Map> querySystemCon(AosConstants bean){
		List<Map> d = null;
		Map map = new HashMap();
		map.put("value", bean.getValue());
		String pid = bean.getPid();
		if((pid == null || "".equals(pid)) && (bean.getValue() == null || "".equals(bean.getValue()))){//pid为空且value为空 以根数据查
			map.put("pid", XConstants.DATA_ROOT_ID);
		}else{
			map.put("pid", bean.getPid());
		}
		d = iAosConstantsDao.querySystemCon(map);
		d = ServiceUtils.dataListFormat(d);
		return d;
	}
	
}