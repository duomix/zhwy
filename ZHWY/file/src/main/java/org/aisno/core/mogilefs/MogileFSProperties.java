package org.aisno.core.mogilefs;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MogileFS 配置
 * @author XZY
 * 2017-1-20-下午4:28:37
 */
@ConfigurationProperties(prefix = "spring.mogilefs")
public class MogileFSProperties {
	
	private String addressesCsv;
	private String domain;
	private int maxActive;
	private int maxIdle;
	private boolean testOnBorrow;
	public String getAddressesCsv() {
		return addressesCsv;
	}
	public void setAddressesCsv(String addressesCsv) {
		this.addressesCsv = addressesCsv;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getMaxActive() {
		return maxActive;
	}
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	public int getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public boolean getTestOnBorrow() {
		return testOnBorrow;
	}
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	
}