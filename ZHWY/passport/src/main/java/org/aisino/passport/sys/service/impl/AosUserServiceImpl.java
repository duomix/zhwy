package org.aisino.passport.sys.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.aisino.core.base.BaseService;
import org.aisino.core.redis.SessionUtils;
import org.aisino.passport.sys.service.IAosUserDutyService;
import org.aisino.passport.sys.service.IAosUserService;
import org.aisino.passport.sys.vo.AosUser;
import org.aisino.passport.sys.vo.AosUserDuty;
import org.aisino.utils.common.MsgMap;
import org.aisino.utils.common.PasswordUtils;
import org.aisino.utils.common.UUID;
import org.aisino.passport.sys.common.XConstants;
import org.aisino.passport.sys.dao.IAosUserDao;

@Service
public class AosUserServiceImpl  extends BaseService<AosUser,IAosUserDao> implements IAosUserService{
	
	@Autowired
	private IAosUserDao iAosUserDao;
	@Autowired
	private IAosUserDutyService iAosUserDutyService;
	@Autowired
	private SessionUtils sessionUtils;
	
	
	/**
	 * 根据用户查询用户可使用系统
	 * @param userid
	 * @return
	 */
	public List queryUserResSystem(String userid){
		List datalist = iAosUserDao.queryUserResSystem(userid);
		return datalist;
	}
	
	
	/**
	 * 根据用户账户查找角色标识集合
	 * @param   account 参数不可为空
	 * @return
	 */
	public List<String> findUserRoleList(String account){
		List<String> datalist = null;
		if(account != null && !"".equals(account)){
			datalist = iAosUserDao.findUserRoleList(account);
		}
		return datalist;
	}
	
	/**
	 * 根据用户账户查资源标识集合
	 * @param   account 参数不可为空
	 * @return
	 */
	public List<String> findUserResList(String account){
		List<String> datalist = null;
		if(account != null && !"".equals(account)){
			datalist = iAosUserDao.findUserResList(account);
		}
		return datalist;
	}
	
	/**
	 * 修改用户密码
	 * @param user  用户对象
	 * @param old_password  旧密码
	 * @param new_password  新密码
	 * @return
	 */
	public Integer updatePassword(AosUser user, String old_password, String new_password){
		Integer c = 0;
		if(user != null && old_password != null && new_password != null){
			old_password = PasswordUtils.MD5Encrypt(user.getAccount(), old_password);
			if(user.getPassword().equals(old_password)){
				new_password = PasswordUtils.MD5Encrypt(user.getAccount(), new_password);
				AosUser tmp = new AosUser();
				tmp.setUserid(user.getUserid());
				tmp.setPassword(new_password);
				c = iAosUserDao.update(tmp);
			}else{
				//原密码不正确提示
				c = -10;
			}
		}
		return c;
	}
	
	
	
	/**
	 * 新增用户
	 * @param bean
	 * @return
	 */
	@Transactional
	public Integer add(AosUser bean){
		Integer c = 0;
		String account = bean.getAccount();
		String password = bean.getPassword();
		if(account != null && password != null && !"".equals(account) && !"".equals(password)){
			//检测用户名是否存在
			AosUser tmp = new AosUser();
			tmp.setAccount(account);
			List dl = iAosUserDao.findList(tmp);
			if(dl != null && dl.size() > 0){
				c = XConstants.SYSTEM_ACCOUNT_EXIST_ERROR_CODE;//用户名已存在
			}else{
				String userid  = UUID.GetUUID32();
				bean.setUserid(userid);//设置主键
				bean.setPassword(PasswordUtils.MD5Encrypt(account, password));//密码加密
				bean.setAvailable(XConstants.DATA_AVAILABLE_YES);
				bean.setStatus(XConstants.ACCOUNT_STATUS_NORMAL);
				//新增用户信息
				c = iAosUserDao.insert(bean);
				//处理用户职务信息
				String dutyid_mian = bean.getDutyid_main();
				String dutyid_assis = bean.getDutyid_assis();
				addUserDuty(userid, dutyid_mian, dutyid_assis);
			}
		}
		return c;
	}
	
	
	/**
	 * 修改用户
	 * @param bean
	 * @return
	 */
	@Transactional
	public int updateInfo(AosUser bean){
		int c = this.update(bean);
		//处理用户职务信息
		String dutyid_mian = bean.getDutyid_main();
		String dutyid_assis = bean.getDutyid_assis();
		addUserDuty(bean.getUserid(), dutyid_mian, dutyid_assis);
		return c;
	}
	
	/**
	 * 登入验证
	 * @param ac 登入账户
	 * @param pa 登录密码明文
	 * @param pa_m 登录密码密文
	 * @return
	 */
	public Map<String,Object> doGetAuthenticationInfo(String ac,String pa,String pa_m){
		if(ac == null || "".equals(ac) || pa == null || "".equals(pa)){
			return MsgMap.getMsg(1, "参数异常", null);
		}
		AosUser u = new AosUser();
		u.setAccount(ac);
		AosUser user = iAosUserDao.get(u);//根据账户查询账户详细信息
		//验证账户是否存在
		if(user == null || user.getUserid()==null){
			return MsgMap.getMsg(2, "账户不存在", null);
		}
		//账户锁定需后台解锁操作
		if(XConstants.ACCOUNT_STATUS_LOCK.equals(user.getStatus())){
			return MsgMap.getMsg(3, "账户被锁定", null);
		}
		//验证密码是否正确         （此处临时处理办法）
		String passport = null;
		if(pa_m != null && !"".equals(pa_m)){
			passport = pa_m;
		}else{
			passport = PasswordUtils.MD5Encrypt(ac, pa);
		}
		
		if(!user.getPassword().equals(passport)){
			return MsgMap.getMsg(4, "账户或密码不正确", null);
		}else{
			//用户信息存放redis
			sessionUtils.putUserInfo(user.getUserid(), user);
			//用户职务存放于session中
			iAosUserDutyService.saveUserDutyToSession(user.getUserid());
			return MsgMap.getMsg(XConstants.MSG_SUCCES_CODE, "登陆成功", user);
		}
	}
	
	/**
	 * 新增用户职务信息 提供给新增用户服务使用
	 * @param userid
	 * @param dutyid_mian
	 * @param dutyid_assis
	 */
	public void addUserDuty(String userid, String dutyid_mian, String dutyid_assis){
		//先删除在新增
		if(userid != null && !"".equals(userid)){
			iAosUserDutyService.delete(userid);
		}
		/**
		 * 新增主职务信息
		 */
		AosUserDuty tmp_ud = new AosUserDuty();
		tmp_ud.setUserid(userid);
		tmp_ud.setIsmain(XConstants.DUTY_TYPE_ASSIS);//默认副职务
		if(dutyid_mian != null && !"".equals(dutyid_mian)){
			tmp_ud.setDutyid(dutyid_mian);
			tmp_ud.setIsmain(XConstants.DUTY_TYPE_MIAN);//设置主职务标识
			iAosUserDutyService.add(tmp_ud);
		}
		/**
		 * 新增副职务信息  多个以逗号隔开
		 */
		if(dutyid_assis != null && !"".equals(dutyid_assis)){
			tmp_ud.setIsmain(XConstants.DUTY_TYPE_ASSIS);//设置副职务标识
			tmp_ud.setDutyid(dutyid_assis);
			iAosUserDutyService.add(tmp_ud);
		}
	}
}