package org.aisino.core.base;

import java.io.Serializable;

/**
 * vo实体公共部分
 */
@SuppressWarnings("serial")
public abstract class BaseDomain implements Serializable {
	/**
	 * 分页参数 每页数据条数
	 */
	private int pageNum;
	/**
	 * 分页参数 页数
	 */
	private int  pageSize;
	/**
	 * 树形结构父节点名称
	 */
	private String pname;
	
	/**
	 * 用于前端传送userid  来至管理端
	 */
	private String key;
	
	/**
	 * 用于前端传递dutyid，职务id
	 */
	private String keyduty;
	
	/**
	 * session id 格式userid_deviceid（用户id_设备id）来至app端
	 */
	private String sessionid;
	
	
	/**
	 * 来源   定义信息来源 web(web端),app(app端),wx(微信端),zfb(支付宝端)
	 */
	private String request_source;
	
	

	public String getRequest_source() {
		return request_source;
	}

	public void setRequest_source(String request_source) {
		this.request_source = request_source;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKeyduty() {
		return keyduty;
	}

	public void setKeyduty(String keyduty) {
		this.keyduty = keyduty;
	}
	
}
