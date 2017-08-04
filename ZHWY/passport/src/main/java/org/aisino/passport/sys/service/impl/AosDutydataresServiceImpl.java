package org.aisino.passport.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.aisino.core.base.BaseService;
import org.aisino.passport.sys.service.IAosDutydataresService;
import org.aisino.passport.sys.vo.AosDutydatares;
import org.aisino.utils.common.UUID;
import org.aisino.passport.sys.dao.IAosDutydataresDao;

@Service
public class AosDutydataresServiceImpl  extends BaseService<AosDutydatares,IAosDutydataresDao> implements IAosDutydataresService{

	
	/**
	 * 新增数据
	 * @param bean
	 * @return
	 */
	@Transactional
	public Integer add(AosDutydatares bean) {
		Integer c = 0;
		String dutyid = bean.getDutyid();
		String deptid = bean.getDeptid();
		if(dutyid != null && !"".equals(dutyid) && deptid != null && !"".equals(deptid)){
			this.delete(dutyid);
			String[] res_s = null;
			if(deptid.indexOf(",") > -1){
				res_s = deptid.split(",");
			}else{
				res_s = new String[1];
				res_s[0] = deptid;
			}
			for(int i=0;i<res_s.length;i++){
				bean.setId(UUID.GetUUID32());//设置主键
				bean.setDeptid(res_s[i]);
				c += this.insert(bean);
			}
		}
		return c;
	}
}