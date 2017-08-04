package org.aisino.apiauth.vo;

import org.aisino.core.base.BaseDomain;

/**
 * api资源
 * @author hexing
 *
 */
public class ApiRes extends BaseDomain {

	private static final long serialVersionUID = 1L;
	
	private   String   id;
	private   String   pid;
	private   String   apiname;
	private   String   apiurl;
	private   String   createdate;
	private   int        available;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getApiname() {
		return apiname;
	}
	public void setApiname(String apiname) {
		this.apiname = apiname;
	}
	public String getApiurl() {
		return apiurl;
	}
	public void setApiurl(String apiurl) {
		this.apiurl = apiurl;
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
	
}
