package org.aisino.passport.sys.service;

import org.aisino.core.base.IBaseService;
import org.aisino.passport.sys.vo.AosDuty;
import org.aisino.passport.sys.vo.AosUserDuty;

public interface IAosUserDutyService extends IBaseService<AosUserDuty>{
	
	/**
	 * 新增用户职务
	 * @param bean
	 * @return
	 */
	public Integer add(AosUserDuty bean) ;
	
	/**
	 * 保存用户职务到session  
	 * @param userid
	 */
	public void saveUserDutyToSession(String userid);
	
	/**
	 * 根据职务id切换职务
	 * @param dutyid
	 */
	public void switcherUserDuty(String dutyid);
	
	/**
	 * 从session中获取当前使用职务
	 * @return
	 */
	public AosDuty getUserDutyMainFormSession();
	
	
	/**
	 * 设置用户主职务
	 * @param userid  用户id
	 * @param dutyid 主职务id
	 * @return
	 */
	public Integer setUserDutyMain(String userid, String dutyid);
}