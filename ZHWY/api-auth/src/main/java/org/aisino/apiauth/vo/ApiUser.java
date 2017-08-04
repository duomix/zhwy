package org.aisino.apiauth.vo;

import java.util.List;

import org.aisino.core.base.BaseDomain;

/**
 * api用户
 * @author hexing
 *
 */
public class ApiUser extends BaseDomain {

	private static final long serialVersionUID = 1L;
	
	private   String   userid;
	private   String   username;
	private   String   account;
	private   String   password;
	private   int   usertype;
	private   String   createdate;
	private   int   available;
	private   List<ApiRes> apis;
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public List<ApiRes> getApis() {
		return apis;
	}
	public void setApis(List<ApiRes> apis) {
		this.apis = apis;
	}

}
