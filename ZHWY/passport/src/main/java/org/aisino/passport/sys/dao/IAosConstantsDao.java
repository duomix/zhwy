package org.aisino.passport.sys.dao;

import java.util.List;
import java.util.Map;
import org.aisino.core.base.BaseDao;
import org.aisino.passport.sys.vo.AosConstants;

public interface IAosConstantsDao extends BaseDao<AosConstants>{
	
	/**
	 * 查询字典树
	 * @param map
	 * @return
	 */
	public List<Map> querySystemCon(Map map);
}