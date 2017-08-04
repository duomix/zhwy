package org.aisino.passport.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.aisino.core.base.BaseService;
import org.aisino.passport.sys.service.IAosRoleResService;
import org.aisino.passport.sys.service.common.ServiceUtils;
import org.aisino.passport.sys.vo.AosRoleRes;
import org.aisino.utils.common.UUID;
import org.aisino.passport.sys.common.XConstants;
import org.aisino.passport.sys.dao.IAosRoleResDao;

@Service
public class AosRoleResServiceImpl  extends BaseService<AosRoleRes,IAosRoleResDao> implements IAosRoleResService{
	
	@Autowired
	private IAosRoleResDao iAosRoleResDao;
	
	/**
	 * 新增角色资源数据
	 * @param bean
	 * @return
	 */
	@Transactional
	public Integer add(AosRoleRes bean) {
		Integer c = 0;
		String roleid = bean.getRoleid();
		String resid = bean.getResid();
		if(roleid != null && !"".equals(roleid) && resid != null && !"".equals(resid)){
			//删除角色资源在新增
			this.delete(roleid);
			String[] res_s = null;
			if(resid.indexOf(",") > -1){
				res_s = resid.split(",");
			}else{
				res_s = new String[1];
				res_s[0] = resid;
			}
			bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
			for(int i=0;i<res_s.length;i++){
				bean.setId(UUID.GetUUID32());//设置主键
				bean.setResid(res_s[i]);
				c += this.insert(bean);
			}
		}
		return c;
	}
	
	
	/**
	 * 查询当前人员职务角色资源并选中角色资源
	 * @param pid  资源pid
	 * @param userid 用户id
	 * @param roleid 角色id
	 * @param dutyid 职务id
	 * @return
	 */
	public List<Map> queryRoleResTree(String pid, String userid, String roleid, String dutyid){
		List<Map> d = null;
		if(roleid != null && !"".equals(roleid)&& userid != null && !"".equals(userid)&& dutyid != null && !"".equals(dutyid)){
			Map<String,String> map = new HashMap<String, String>();
			if((pid == null || "".equals(pid))){
				map.put("pid", XConstants.DATA_ROOT_ID);
			}else{
				map.put("pid", pid);
			}
			map.put("userid", userid);
			map.put("roleid", roleid);
			map.put("dutyid", dutyid);
			d = iAosRoleResDao.queryRoleResTree(map);
			d = ServiceUtils.dataListFormat(d);
		}
		return d;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> queryRoleRes(String roleid, String userid) {
		if(roleid==null || roleid.isEmpty()){
			return null;
		}
		Map<String,String> map = new HashMap<String, String>();
		map.put("roleid", roleid);
		map.put("userid",userid);
		List<Map> list = iAosRoleResDao.queryRoleRes(map);
		list = ServiceUtils.listToTree(list);
		return  list;
	}
	
}