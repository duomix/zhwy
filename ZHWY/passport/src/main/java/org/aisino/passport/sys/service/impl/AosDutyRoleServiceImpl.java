package org.aisino.passport.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.aisino.core.base.BaseService;
import org.aisino.passport.sys.service.IAosDutyRoleService;
import org.aisino.passport.sys.vo.AosDutyRole;
import org.aisino.utils.common.UUID;
import org.aisino.passport.sys.common.XConstants;
import org.aisino.passport.sys.dao.IAosDutyRoleDao;

@Service
public class AosDutyRoleServiceImpl  extends BaseService<AosDutyRole,IAosDutyRoleDao> implements IAosDutyRoleService{

	/**
	 * 新增数据
	 * @param bean
	 * @return
	 */
	@Transactional
	public Integer add(AosDutyRole bean) {
		Integer c = 0;
		String dutyid = bean.getDutyid();
		String roleid = bean.getRoleid();
		if(dutyid != null && !"".equals(dutyid) && roleid != null && !"".equals(roleid)){
			//删除职务角色在新增
			this.delete(dutyid);
			String[] res_s = null;
			if(roleid.indexOf(",") > -1){
				res_s = roleid.split(",");
			}else{
				res_s = new String[1];
				res_s[0] = roleid;
			}
			bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
			for(int i=0;i<res_s.length;i++){
				bean.setId(UUID.GetUUID32());//设置主键
				bean.setRoleid(res_s[i]);
				c += this.insert(bean);
			}
		}
		return c;
	}
}