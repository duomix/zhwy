package org.aisino.passport.sys.service;

import org.aisino.core.base.IBaseService;
import org.aisino.passport.sys.vo.AosDutydatares;

public interface IAosDutydataresService extends IBaseService<AosDutydatares>{
	
	/**
	 * 新增数据
	 * @param bean
	 * @return
	 */
	public Integer add(AosDutydatares bean) ;
	
}