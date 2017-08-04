package org.aisino.passport.sys.service;


import org.aisino.core.base.IBaseService;
import org.aisino.passport.sys.vo.AosRole;

public interface IAosRoleService extends IBaseService<AosRole>{
	
	/**
	 * 新增数据
	 * @param bean
	 * @return
	 */
	public Integer add(AosRole bean);
	
	
	/**
	 * 修改数据
	 * @param bean
	 * @return
	 */
	public int updateinfo(AosRole bean);
	
}