package org.aisino.passport.sys.dao;

import java.util.List;
import java.util.Map;

import org.aisino.core.base.BaseDao;
import org.aisino.passport.sys.vo.AosDuty;

public interface IAosDutyDao extends BaseDao<AosDuty>{

	List<Map<String, Object>> queryDutyTree(String deptid);

}