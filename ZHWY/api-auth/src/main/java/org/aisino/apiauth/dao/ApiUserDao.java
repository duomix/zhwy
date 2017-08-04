package org.aisino.apiauth.dao;

import org.aisino.apiauth.vo.ApiUser;
import org.aisino.core.base.BaseDao;

public interface ApiUserDao extends BaseDao<ApiUser> {

	ApiUser getUserByAccount(String account);
}
