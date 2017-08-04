package org.aisino.passport.sys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.aisino.core.base.BaseService;
import org.aisino.passport.sys.service.IAosRoleResService;
import org.aisino.passport.sys.service.IAosRoleService;
import org.aisino.passport.sys.vo.AosRole;
import org.aisino.passport.sys.vo.AosRoleRes;
import org.aisino.utils.common.DateUtils;
import org.aisino.passport.sys.common.XConstants;
import org.aisino.passport.sys.dao.IAosRoleDao;

@Service
public class AosRoleServiceImpl  extends BaseService<AosRole,IAosRoleDao> implements IAosRoleService{

	@Autowired
	private IAosRoleDao iAosRoleDao;
	@Autowired
	private IAosRoleResService iAosRoleResService;
	
	/**
	 * 新增数据
	 * @param bean
	 * @return
	 */
	@Transactional
	public Integer add(AosRole bean){
		String rolecode = bean.getRolecode();
		if(rolecode != null && !"".equals(rolecode)){//资源编码不为空时检测是否以存在
			AosRole tmp = new AosRole();
			tmp.setRolecode(rolecode);
			tmp.setAvailable(XConstants.DATA_AVAILABLE_YES);
			List dl = iAosRoleDao.findList(tmp);
			if(dl != null && dl.size() > 0){
				return XConstants.SYSTEM_ROLE_EXIST_ERROR_CODE;
			}
		}
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		bean.setCreatedate(DateUtils.getFormatDate_DT());
		//新增角色信息
		Integer c = iAosRoleDao.insert(bean);
		//处理角色资源信息
		String roleid = bean.getRoleid();
		String resid = bean.getResid();
		addRoleRes(roleid, resid);
		return c;
	}
	
	
	/**
	 * 修改数据
	 * @param bean
	 * @return
	 */
	public int updateinfo(AosRole bean){
		int c = 0;
		//修改角色信息
		c = this.update(bean);
		//处理角色资源信息
		String roleid = bean.getRoleid();
		String resid = bean.getResid();
		addRoleRes(roleid, resid);
		return c;
	}
	
	/**
	 * 处理角色资源管理
	 * @param roleid  角色id
	 * @param resid  角色资源id  多个以逗号隔开
	 */
	public void addRoleRes(String roleid, String resid){
		if(resid != null && !"".equals(resid)){
			AosRoleRes rr = new AosRoleRes();
			rr.setRoleid(roleid);
			rr.setResid(resid);
			//新增角色资源信息
			iAosRoleResService.add(rr);
		}
	}
}