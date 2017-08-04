package org.aisino.passport.sys.service;

import java.util.List;
import java.util.Map;

import org.aisino.core.base.IBaseService;
import org.aisino.passport.sys.vo.AosUser;

public interface IAosUserService extends IBaseService<AosUser>{
	
	/**
	 * 根据用户账户查找角色标识集合
	 * @param   account 参数不可为空
	 * @return
	 */
	public List<String> findUserRoleList(String account);
	
	/**
	 * 根据用户账户查资源标识集合
	 * @param   account 参数不可为空
	 * @return
	 */
	public List<String> findUserResList(String account);
	
	/**
	 * 修改用户密码
	 * @param user  用户对象
	 * @param old_password  旧密码
	 * @param new_password  新密码
	 * @return
	 */
	public Integer updatePassword(AosUser user, String old_password, String new_password);
	
	/**
	 * 新增用户
	 * @param bean
	 * @return
	 */
	public Integer add(AosUser bean);
	
	
	/**
	 * 修改用户
	 * @param bean
	 * @return
	 */
	public int updateInfo(AosUser bean);
	
	
	/**
	 * 登入验证
	 * @param ac 登入账户
	 * @param pa 登录密码明文
	 * @param pa_m 登录密码密文
	 * @return
	 */
	public Map<String,Object> doGetAuthenticationInfo(String ac,String pa,String pa_m);
	
	
	/**
	 * 根据用户查询用户可使用系统
	 * @param userid
	 * @return
	 */
	public List queryUserResSystem(String userid);
}