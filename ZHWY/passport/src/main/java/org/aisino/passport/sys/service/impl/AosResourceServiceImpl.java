package org.aisino.passport.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.aisino.core.base.BaseService;
import org.aisino.passport.sys.service.IAosResourceService;
import org.aisino.passport.sys.service.IAosUserService;
import org.aisino.passport.sys.service.common.ServiceUtils;
import org.aisino.passport.sys.vo.AosResource;
import org.aisino.passport.sys.vo.AosUser;
import org.aisino.utils.common.UUID;
import org.aisino.passport.sys.common.XConstants;
import org.aisino.passport.sys.dao.IAosResourceDao;

@Service
public class AosResourceServiceImpl  extends BaseService<AosResource,IAosResourceDao> implements IAosResourceService{
	
	@Autowired
	private IAosResourceDao iAosResourceDao;
	@Autowired
	private IAosUserService iAosUserService;
	
	/**
	 * 查询系统资源
	 * 查询超级管理员 map:key:pid=0
	 * 一般管理员 map:key:pid=参数pid map:key:userid=参数userid
	 * 业务人员只能查询业务类型资源
	 * @param map
	 * @return
	 */
	public List<Map> querySystemRes(AosResource bean){
		List<Map> d = null;
		AosUser user = new AosUser();
		user.setUserid(bean.getKey());
		user = iAosUserService.get(user);
		String issys = user.getIssys();//是否超级管理员
		String identitytype = user.getIdentitytype();//身份类别
		if(identitytype == null || "".equals(identitytype)){
			return d;
		}
		Map map = new HashMap();
		map.put("restype", bean.getRestype());
		map.put("resname", bean.getResname());
		String pid = bean.getPid();
		if((pid == null || "".equals(pid)) && (bean.getResname() == null  || "".equals(bean.getResname()))){//pid为空且resname为空 以根数据查
			map.put("pid", XConstants.DATA_ROOT_ID);
		}else{
			map.put("pid", bean.getPid());
		}
		if("1".equals(issys)){//1:超级管理员
		}else if(XConstants.IDENTITYTYPE_ADMIN.equals(identitytype)){//管理员
			map.put("userid", user.getUserid());
		}else if(XConstants.IDENTITYTYPE_BUSSINES.equals(identitytype)){//业务人员
			map.put("userid", user.getUserid());
			map.put("identitytype", XConstants.IDENTITYTYPE_BUSSINES);
			map.put("rescode", bean.getRescode());
		}
		d = iAosResourceDao.querySystemRes(map);
		d = ServiceUtils.dataListFormat(d);
		return d;
	}
	
	
	
	/**
	 * 新增数据
	 * @param bean
	 * @return
	 */
	public Integer add(AosResource bean){
		String rescode = bean.getRescode();
		if(rescode != null && !"".equals(rescode)){//资源编码不为空时检测是否以存在
			AosResource tmp = new AosResource();
			tmp.setRescode(rescode);
			tmp.setAvailable(XConstants.DATA_AVAILABLE_YES);
			List dl = iAosResourceDao.findList(tmp);
			if(dl != null && dl.size() > 0){
				return XConstants.SYSTEM_RESOURCE_EXIST_ERROR_CODE;
			}
		}
		bean.setResid(UUID.GetUUID32());//设置主键
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		Integer c = iAosResourceDao.insert(bean);
		return c;
	}
	
	
	/**
	 * 查询系统用户菜单
	 * @描述: 必传参数  pid  userid
	 * @描述:
	 * @方法名: queryUserRes
	 * @param respid
	 * @param userid
	 * @return
	 * @返回类型 List<Map>
	 * @创建人 XZY
	 * @创建时间 2017-7-27下午2:33:41
	 * @修改人 XZY
	 * @修改时间 2017-7-27下午2:33:41
	 * @修改备注
	 * @since
	 * @throws
	 */
	public List<Map> queryUserRes(String respid, String userid){
		List<Map> d = null;
		if(respid == null || "".equals(respid)){//资源pid 默认根
			respid = XConstants.DATA_ROOT_ID;
		}
		Map m = new HashMap();
		m.put("pid", respid);
		m.put("userid", userid);
		d = iAosResourceDao.queryUserRes(m);
		queryUserResUtils(d,userid);
		return d;
	}
	//查询系统用户菜单工具递归出所有资源菜单
	public void queryUserResUtils(List<Map> d, String userid){
		if(d != null && d.size() > 0){
			for(int i = 0;i < d.size();i++){
				Map tmp = d.get(i);
				String resid = tmp.get("resid").toString();
				Map m = new HashMap();
				m.put("pid", resid);
				m.put("userid", userid);
				List<Map> tmp_m = iAosResourceDao.queryUserRes(m);
				tmp.put("children", tmp_m);
				queryUserResUtils(tmp_m,userid);
			}
		}
	}
	
}