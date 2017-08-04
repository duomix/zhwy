package org.aisino.apiauth.service.impl;

import org.aisino.apiauth.dao.ApiUserDao;
import org.aisino.apiauth.service.ApiUserService;
import org.aisino.apiauth.vo.ApiUser;
import org.aisino.core.base.BaseService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ApiUserServiceImpl extends BaseService<ApiUser, ApiUserDao> implements ApiUserService {
	
	@Cacheable(value="apiuser",key="#account")
	@Override
	public ApiUser getUserByAccount(String account) {
		return dao.getUserByAccount(account);
	}

	@CacheEvict(value="apiuser",allEntries=true)
	@Override
	public void delcache() {}
	
	
}
