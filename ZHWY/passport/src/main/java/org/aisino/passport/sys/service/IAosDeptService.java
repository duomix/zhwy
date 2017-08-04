package org.aisino.passport.sys.service;

import java.util.List;
import java.util.Map;
import org.aisino.core.base.IBaseService;
import org.aisino.passport.sys.vo.AosDept;

public interface IAosDeptService extends IBaseService<AosDept>{
	
	
	/**
	 * 查询组织机构树
	 * @param bean
	 * @return
	 */
	public List<Map> querySystemDept(AosDept bean);
	
	/**
	 * 新增数据
	 * @param bean
	 * @return
	 */
	public Integer add(AosDept bean) ;
	
	/**
	 * 删除数据
	 * @param bean
	 * @return
	 */
	public Integer del(String id) ;
	
	
	/**
	 * 根据组织结构id判断 当前组织机构下是否存在职务
	 * @param deptid 组织机构id
	 * @return   1:存在   0:不存在
	 */
	public Integer isExistDuty(String deptid);
}