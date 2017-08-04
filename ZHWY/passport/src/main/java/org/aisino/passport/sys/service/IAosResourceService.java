package org.aisino.passport.sys.service;

import java.util.List;
import java.util.Map;
import org.aisino.core.base.IBaseService;
import org.aisino.passport.sys.vo.AosResource;

public interface IAosResourceService extends IBaseService<AosResource>{
	
	/**
	 * 查询系统资源
	 * @param map
	 * @return
	 */
	public List<Map> querySystemRes(AosResource bean);
	
	/**
	 * 新增数据
	 * @param bean
	 * @return
	 */
	public Integer add(AosResource bean);
	
	
	/**
	 * 查询系统用户菜单
	 * @描述: 必传参数  pid  userid
	 * @描述:
	 * @方法名: queryUserRes
	 * @param respid
	 * @param userid
	 * @return
	 * @返回类型 List<Map>
	 * @创建人 XZY
	 * @创建时间 2017-7-27下午2:33:41
	 * @修改人 XZY
	 * @修改时间 2017-7-27下午2:33:41
	 * @修改备注
	 * @since
	 * @throws
	 */
	public List<Map> queryUserRes(String respid, String userid);
}