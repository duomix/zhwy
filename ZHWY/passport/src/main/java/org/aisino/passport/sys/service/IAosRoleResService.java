package org.aisino.passport.sys.service;

import java.util.List;
import java.util.Map;
import org.aisino.core.base.IBaseService;
import org.aisino.passport.sys.vo.AosRoleRes;

public interface IAosRoleResService extends IBaseService<AosRoleRes>{
	
	/**
	 * 新增数据
	 * @param bean
	 * @return
	 */
	public Integer add(AosRoleRes bean) ;
	
	
	/**
	 * 查询当前人员职务角色资源并选中角色资源
	 * @param pid  资源pid
	 * @param userid 用户id
	 * @param roleid 角色id
	 * @param dutyid 职务id
	 * @return
	 */
	public List<Map> queryRoleResTree(String pid, String userid, String roleid, String dutyid);

	/**
	 * 查询角色对应的资源树，包含勾选状态
	 * @param roleid
	 * @param userid
	 * @return
	 */
	public List<Map> queryRoleRes(String roleid, String userid);
}