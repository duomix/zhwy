package org.aisino.passport.sys.service;

import org.aisino.core.base.IBaseService;
import org.aisino.passport.sys.vo.AosDutyRole;

public interface IAosDutyRoleService extends IBaseService<AosDutyRole>{
	
	/**
	 * 新增数据
	 * @param bean
	 * @return
	 */
	public Integer add(AosDutyRole bean) ;
}