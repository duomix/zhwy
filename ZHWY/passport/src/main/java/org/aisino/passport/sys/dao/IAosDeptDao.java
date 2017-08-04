package org.aisino.passport.sys.dao;

import java.util.List;
import java.util.Map;
import org.aisino.core.base.BaseDao;
import org.aisino.passport.sys.vo.AosDept;

public interface IAosDeptDao extends BaseDao<AosDept>{
	
	/**
	 * 查询组织机构树
	 * @param map
	 * @return
	 */
	public List<Map> querySystemDept(Map map);
}