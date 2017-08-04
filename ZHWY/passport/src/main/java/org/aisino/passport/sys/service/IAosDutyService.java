package org.aisino.passport.sys.service;

import java.util.List;
import java.util.Map;

import org.aisino.core.base.IBaseService;
import org.aisino.passport.sys.vo.AosDuty;

public interface IAosDutyService extends IBaseService<AosDuty>{

	List<Map<String, Object>> queryDutyTree(String deptid);

}