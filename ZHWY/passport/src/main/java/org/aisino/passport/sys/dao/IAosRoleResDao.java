package org.aisino.passport.sys.dao;

import java.util.List;
import java.util.Map;
import org.aisino.core.base.BaseDao;
import org.aisino.passport.sys.vo.AosRoleRes;

@SuppressWarnings("rawtypes")
public interface IAosRoleResDao extends BaseDao<AosRoleRes>{
	
	/**
	 * 查询当前人员职务角色资源并选中角色资源
	 * @param map
	 * @return
	 */
	public List<Map> queryRoleResTree(Map map);

	/**
	 * 查询角色资源树
	 * @param map
	 * @return
	 */
	public List<Map> queryRoleRes(Map<String, String> map);
}