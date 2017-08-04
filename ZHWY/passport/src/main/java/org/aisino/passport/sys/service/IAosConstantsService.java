package org.aisino.passport.sys.service;

import java.util.List;
import java.util.Map;
import org.aisino.core.base.IBaseService;
import org.aisino.passport.sys.vo.AosConstants;

public interface IAosConstantsService extends IBaseService<AosConstants>{
	
	/**
	 * 查询系统登录页面
	 * @return
	 */
	public Map<String,Object> querySystemLoginPage();
	
	/**
	 * 查询系统版权信息
	 * @return
	 */
	public String querySystemCopyRight();
	
	
	/**
	 * 查询字典树
	 * @param map
	 * @return
	 */
	public List<Map> querySystemCon(AosConstants bean);
}
