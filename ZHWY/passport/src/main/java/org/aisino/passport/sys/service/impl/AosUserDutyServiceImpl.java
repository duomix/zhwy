package org.aisino.passport.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.aisino.core.base.BaseService;
import org.aisino.core.redis.RedisUtils;
import org.aisino.passport.sys.service.IAosDutyService;
import org.aisino.passport.sys.service.IAosUserDutyService;
import org.aisino.passport.sys.vo.AosDuty;
import org.aisino.passport.sys.vo.AosUserDuty;
import org.aisino.utils.common.JsonUtil;
import org.aisino.utils.common.UUID;
import org.aisino.passport.sys.common.XConstants;
import org.aisino.passport.sys.dao.IAosUserDutyDao;

@Service
public class AosUserDutyServiceImpl  extends BaseService<AosUserDuty,IAosUserDutyDao> implements IAosUserDutyService{

	@Autowired
	private IAosUserDutyService iAosUserDutyService;
	@Autowired
	private IAosDutyService iAosDutyService;
	
	@Autowired
	private RedisUtils redisUtils;
	
	/**
	 * 新增数据
	 * @param bean
	 * @return
	 */
	@Transactional
	public Integer add(AosUserDuty bean) {
		Integer c = 0;
		String userid = bean.getUserid();
		String dutyid = bean.getDutyid();
		if(userid != null && !"".equals(userid) && dutyid != null && !"".equals(dutyid)){
			String[] res_s = null;
			if(dutyid.indexOf(",") > -1){
				res_s = dutyid.split(",");
			}else{
				res_s = new String[1];
				res_s[0] = dutyid;
			}
			bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
			for(int i=0;i<res_s.length;i++){
				bean.setId(UUID.GetUUID32());//设置主键
				bean.setDutyid(res_s[i]);
				c += this.insert(bean);
			}
		}
		return c;
	}
	
	
	
	/**
	 * 保存用户职务到session  
	 * @param userid
	 */
	public void saveUserDutyToSession(String userid){
		if(userid != null && !"".equals(userid)){//根据userid查询用户职务
			Map<String,String> dutymap = new HashMap<String,String>();
			AosUserDuty ud = new AosUserDuty();
			ud.setAvailable(XConstants.DATA_AVAILABLE_YES);
			ud.setUserid(userid);
			List<AosUserDuty> dl= iAosUserDutyService.findList(ud);
			if(dl != null && dl.size() > 0){
				for(int i=0;i<dl.size();i++){
					AosUserDuty tmp = dl.get(i);
					AosDuty d = new AosDuty();
					d.setAvailable(XConstants.DATA_AVAILABLE_YES);
					d.setDutyid(tmp.getDutyid());
					d = iAosDutyService.get(d);
					if(d != null){
						dutymap.put(d.getDutyid(), JsonUtil.bean2json(d));
						if("1".equals(tmp.getIsmain())){
							redisUtils.setHashToValue(redisUtils.hash, XConstants.USER_DUTY_MAIN, JsonUtil.bean2json(d));//主职务(当前使用职务)
						}
					}
				}
			}
			redisUtils.setHashToValue(redisUtils.hash, XConstants.USER_DUTY,dutymap);//用户职务  dutyid作为map key
		}
	}
	
	
	/**
	 * 根据职务id切换职务
	 * @param dutyid
	 */
	public void switcherUserDuty(String dutyid){
		if(dutyid != null && !"".equals(dutyid)){
			Map<String,String> dutymap = (Map<String, String>) redisUtils.getHashToValue(redisUtils.hash, XConstants.USER_DUTY);
			String aosduty_json =  dutymap.get(dutyid);
			redisUtils.setHashToValue(redisUtils.hash, XConstants.USER_DUTY_MAIN, aosduty_json);//主职务(当前使用职务)
		}
	}
	
	
	/**
	 * 从redis中获取当前主要职务
	 * @return
	 */
	public AosDuty getUserDutyMainFormSession(){
		AosDuty d = new AosDuty();
		Object tmp = redisUtils.getHashToValue(redisUtils.hash, XConstants.USER_DUTY_MAIN);
		if(tmp != null){
			d = JsonUtil.JsonObjStr2JavaBean(tmp.toString(), AosDuty.class);
		}
		return d;
	}
	
	
	/**
	 * 设置用户主职务
	 * @param userid  用户id
	 * @param dutyid 主职务id
	 * @return
	 */
	public Integer setUserDutyMain(String userid, String dutyid){
		Integer c = 0;
		if(userid != null && dutyid != null && !"".equals(userid) && !"".equals(dutyid)){
			//清空ismain标识
			AosUserDuty ud = new AosUserDuty();
			ud.setUserid(userid);
			ud.setIsmain("0");
			this.update(ud);
			//设置指定dutyid ismain为1
			ud.setIsmain("1");
			ud.setDutyid(dutyid);
			c = this.update(ud);
		}
		return c;
	}
	
}