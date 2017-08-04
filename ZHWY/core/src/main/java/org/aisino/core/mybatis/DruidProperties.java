package org.aisino.core.mybatis;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.datasource")
public class DruidProperties {
	private String url;
    private String username;
    private String password;
    
    /**
     * 连接池的初始化大小，最小，最大
     */
    private int initialSize=1;
    private int minIdle=1;
    private int maxActive=20;
    
    /**
     * 配置获取连接等待超时的时间
     */
    private long maxWait=60000;
    
    /**
     * 查询超时时间
     */
    private int queryTimeout=60000;
    
    /**
     *事务查询超时时间
     */
    private int  transactionQueryTimeout=60000;
    
    /**
     * 登陆超时时间
     */
    private int loginTimeout=2000;
    
    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 
     */
    private long timeBetweenEvictionRunsMillis=60000;
    
    /**
     *配置一个连接在池中最小生存的时间，单位是毫秒 
     */
    private long minEvictableIdleTimeMillis=300000;
   
    private String validationQuery="SELECT 1 FROM DUAL";
    
    /**
     * 是否在连接空闲一段时间后检测其可用性
     */
    private boolean testWhileIdle=true;
    
    /**
     * 	是否在获得连接后检测其可用性
     */
    private boolean testOnBorrow=false;
    
    /**
     * 是否在连接放回连接池后检测其可用性
     */
    private boolean testOnReturn=false;
    
    /**
     * 打开PSCache，并且指定每个连接上PSCache的大小(MySQL 不	支持)
     */
    private boolean poolPreparedStatements=false;
    private int maxPoolPreparedStatementPerConnectionSize;
    
    /**
     *  配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
     */
    private String filters="stat";
    
  /**
   * 通过connectProperties属性来打开mergeSql功能；慢SQL记录
   */
    private String connectionProperties;

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getInitialSize() {
		return initialSize;
	}
	
	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}
	
	public int getMinIdle() {
		return minIdle;
	}
	
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
	
	public int getMaxActive() {
		return maxActive;
	}
	
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	
	public long getMaxWait() {
		return maxWait;
	}
	
	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}
	
	public int getQueryTimeout() {
		return queryTimeout;
	}
	
	public void setQueryTimeout(int queryTimeout) {
		this.queryTimeout = queryTimeout;
	}
	
	public int getTransactionQueryTimeout() {
		return transactionQueryTimeout;
	}
	
	public void setTransactionQueryTimeout(int transactionQueryTimeout) {
		this.transactionQueryTimeout = transactionQueryTimeout;
	}
	
	public int getLoginTimeout() {
		return loginTimeout;
	}
	
	public void setLoginTimeout(int loginTimeout) {
		this.loginTimeout = loginTimeout;
	}
	
	public long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}
	
	public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}
	
	public long getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}
	
	public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}
	
	public String getValidationQuery() {
		return validationQuery;
	}
	
	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}
	
	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}
	
	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}
	
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}
	
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	
	public boolean isTestOnReturn() {
		return testOnReturn;
	}
	
	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}
	
	public boolean isPoolPreparedStatements() {
		return poolPreparedStatements;
	}
	
	public void setPoolPreparedStatements(boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}
	
	public int getMaxPoolPreparedStatementPerConnectionSize() {
		return maxPoolPreparedStatementPerConnectionSize;
	}
	
	public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
		this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
	}
	
	public String getFilters() {
		return filters;
	}
	
	public void setFilters(String filters) {
		this.filters = filters;
	}
	
	public String getConnectionProperties() {
		return connectionProperties;
	}
	
	public void setConnectionProperties(String connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DruidProperties [url=");
		builder.append(url);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", initialSize=");
		builder.append(initialSize);
		builder.append(", minIdle=");
		builder.append(minIdle);
		builder.append(", maxActive=");
		builder.append(maxActive);
		builder.append(", maxWait=");
		builder.append(maxWait);
		builder.append(", queryTimeout=");
		builder.append(queryTimeout);
		builder.append(", transactionQueryTimeout=");
		builder.append(transactionQueryTimeout);
		builder.append(", loginTimeout=");
		builder.append(loginTimeout);
		builder.append(", timeBetweenEvictionRunsMillis=");
		builder.append(timeBetweenEvictionRunsMillis);
		builder.append(", minEvictableIdleTimeMillis=");
		builder.append(minEvictableIdleTimeMillis);
		builder.append(", validationQuery=");
		builder.append(validationQuery);
		builder.append(", testWhileIdle=");
		builder.append(testWhileIdle);
		builder.append(", testOnBorrow=");
		builder.append(testOnBorrow);
		builder.append(", testOnReturn=");
		builder.append(testOnReturn);
		builder.append(", poolPreparedStatements=");
		builder.append(poolPreparedStatements);
		builder.append(", maxPoolPreparedStatementPerConnectionSize=");
		builder.append(maxPoolPreparedStatementPerConnectionSize);
		builder.append(", filters=");
		builder.append(filters);
		builder.append(", connectionProperties=");
		builder.append(connectionProperties);
		builder.append("]");
		return builder.toString();
	}
	
}