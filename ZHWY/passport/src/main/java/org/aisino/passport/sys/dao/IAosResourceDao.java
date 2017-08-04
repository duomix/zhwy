package org.aisino.passport.sys.dao;

import java.util.List;
import java.util.Map;
import org.aisino.core.base.BaseDao;
import org.aisino.passport.sys.vo.AosResource;

public interface IAosResourceDao extends BaseDao<AosResource>{
	
	/**
	 * 查询系统资源
	 * @param map
	 * @return
	 */
	public List<Map> querySystemRes(Map map);
	
	
	/**
	 * 查询系统用户菜单
	 * @描述: 必传参数  pid  userid
	 * @方法名: queryUserRes
	 * @param map
	 * @return
	 * @返回类型 List<Map>
	 * @创建人 XZY
	 * @创建时间 2017-7-27下午2:31:40
	 * @修改人 XZY
	 * @修改时间 2017-7-27下午2:31:40
	 * @修改备注
	 * @since
	 * @throws
	 */
	public List<Map> queryUserRes(Map map);
}