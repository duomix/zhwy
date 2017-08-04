package org.aisino.passport.sys.dao;

import java.util.List;
import org.aisino.core.base.BaseDao;
import org.aisino.passport.sys.vo.AosUser;
import org.apache.ibatis.annotations.Param;

public interface IAosUserDao extends BaseDao<AosUser>{
	
	/**
	 * 根据用户账户查找角色标识集合
	 * @param user
	 * @return
	 */
	public List<String> findUserRoleList(@Param("account") String account);
	
	/**
	 * 根据用户账户查找资源标识集合
	 * @param user
	 * @return
	 */
	public List<String> findUserResList(@Param("account") String account);
	
	
	/**
	 * 根据用户查询用户可使用系统
	 * @param userid
	 * @return
	 */
	public List queryUserResSystem(String userid);
}