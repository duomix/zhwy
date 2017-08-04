package org.aisino.passport.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.aisino.core.base.BaseService;
import org.aisino.passport.sys.service.IAosDeptService;
import org.aisino.passport.sys.service.IAosDutyService;
import org.aisino.passport.sys.service.common.ServiceUtils;
import org.aisino.passport.sys.vo.AosDept;
import org.aisino.passport.sys.vo.AosDuty;
import org.aisino.passport.sys.vo.AosUser;
import org.aisino.utils.common.UUID;
import org.aisino.passport.sys.common.XConstants;
import org.aisino.passport.sys.dao.IAosDeptDao;

@Service
public class AosDeptServiceImpl  extends BaseService<AosDept,IAosDeptDao> implements IAosDeptService{

	@Autowired
	private IAosDeptDao iAosDeptDao;
	@Autowired
	private IAosDutyService iAosDutyService;
	
	/**
	 * 查询组织机构树 当xid为1 时勾选用户数据权限
	 * @param bean
	 * @return
	 */
	public List<Map> querySystemDept(AosDept bean){
		List<Map> d = null;
		Map map = new HashMap();
		map.put("deptname", bean.getDeptname());
		String pid = bean.getPid();
		if((pid == null || "".equals(pid)) && (bean.getDeptname() == null || "".equals(bean.getDeptname()))){//pid为空且deptname为空 以当前职务所属部门为根数据查
			map.put("pid", bean.getDeptid());
		}else{
			map.put("pid", bean.getPid());
		}
		String xid = bean.getXid();
		if("1".equals(xid)){
			map.put("userid", bean.getKey());
		}
		d = iAosDeptDao.querySystemDept(map);
		d = ServiceUtils.dataListFormat(d);
		return d;
	}
	
	/**
	 * 新增数据
	 * @param bean
	 * @return
	 */
	@Transactional
	public Integer add(AosDept bean) {
		bean.setDeptid(UUID.GetUUID32());//设置主键
		bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
		bean.setLeaf_node(XConstants.LEAF_NODE_YES);//新增数据默认叶子节点
		Integer c = iAosDeptDao.insert(bean);
		//新增完成后修改父节点 leaf_node 为1不是叶子节点
		AosDept f_tmp = new AosDept();
		f_tmp.setDeptid(bean.getPid());
		f_tmp.setLeaf_node(XConstants.LEAF_NODE_NO);
		iAosDeptDao.update(f_tmp);
		return c;
	}
	
	
	/**
	 * 删除数据   非事务操作
	 * 并修改父节点leaf_node字段
	 * @param bean
	 * @return
	 */
	public Integer del(String id) {
		Integer c = 0;
		if(id != null && !"".equals(id)){
			AosDept tmp = new AosDept();
			tmp.setDeptid(id);
			tmp = iAosDeptDao.get(tmp);
			String pid = tmp.getPid(); 
			//删除本数据
			c = iAosDeptDao.delete(id);
			//检测父节点下是否有子节点
			AosDept tmp_z = new AosDept();
			tmp_z.setPid(pid);
			List dl = iAosDeptDao.findList(tmp_z);
			if(dl != null && dl.size() > 0){
			}else{
				//修改父节点的 leaf_node 为叶子节点
				tmp_z.setPid(null);
				tmp_z.setDeptid(pid);
				tmp_z.setLeaf_node(XConstants.LEAF_NODE_YES);
				iAosDeptDao.update(tmp_z);
			}
		}
		return c;
	}
	
	/**
	 * 根据组织结构id判断 当前组织机构下是否存在职务
	 * @param deptid 组织机构id
	 * @return   1:存在   0:不存在
	 */
	public Integer isExistDuty(String deptid){
		int r = 0;
		AosDuty ad = new AosDuty();
		ad.setDeptid(deptid);
		List d = iAosDutyService.findList(ad);
		if(d != null && d.size() > 0){
			r = 1;
		}
		return r;
	}
}