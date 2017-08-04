package org.aisino.passport.sys.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import org.aisino.core.base.BaseService;
import org.aisino.passport.sys.service.IAosDutyService;
import org.aisino.passport.sys.vo.AosDuty;
import org.aisino.passport.sys.dao.IAosDutyDao;

@Service
public class AosDutyServiceImpl  extends BaseService<AosDuty,IAosDutyDao> implements IAosDutyService{

	@Override
	public List<Map<String, Object>> queryDutyTree(String deptid) {
		return dao.queryDutyTree(deptid);
	}


}