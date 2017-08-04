package org.aisino.apiauth.service;

import org.aisino.apiauth.vo.ApiUser;
import org.aisino.core.base.IBaseService;

public interface ApiUserService extends IBaseService<ApiUser> {

	ApiUser getUserByAccount(String account);
	
	void   delcache();
}
