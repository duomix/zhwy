package org.aisino.core.mybatis;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * Druid数据源集成
 * @author hexing
 * @since 2016-12-13
 */
@Configuration
@EnableConfigurationProperties(DruidProperties.class)
public class DruidDBConfig {
	private Logger logger = LoggerFactory.getLogger("Druid数据源配置：");
	
	@Autowired
	DruidProperties  conf;
	
    @Bean     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() throws SQLException{
    	DruidDataSource datasource = new DruidDataSource();
    	
    	logger.info(conf.toString());
    	datasource.setUrl(conf.getUrl());
    	datasource.setUsername(conf.getUsername());
    	datasource.setPassword(conf.getPassword());
    	
    	datasource.setInitialSize(conf.getInitialSize());
    	datasource.setMinIdle(conf.getMinIdle());
    	datasource.setMaxActive(conf.getMaxActive());
    	datasource.setMaxWait(conf.getMaxWait());
    	datasource.setTimeBetweenEvictionRunsMillis(conf.getTimeBetweenEvictionRunsMillis());
    	datasource.setMinEvictableIdleTimeMillis(conf.getMinEvictableIdleTimeMillis());
    	datasource.setValidationQuery(conf.getValidationQuery());
    	datasource.setTestWhileIdle(conf.isTestWhileIdle());
    	datasource.setTestOnBorrow(conf.isTestOnBorrow());
    	datasource.setTestOnReturn(conf.isTestOnReturn());
    	datasource.setQueryTimeout(conf.getQueryTimeout());
    	datasource.setTransactionQueryTimeout(conf.getTransactionQueryTimeout());
    	datasource.setLoginTimeout(conf.getLoginTimeout());
    	if(conf.isPoolPreparedStatements()){
    		datasource.setPoolPreparedStatements(conf.isPoolPreparedStatements());
        	datasource.setMaxPoolPreparedStatementPerConnectionSize(conf.getMaxPoolPreparedStatementPerConnectionSize());
    	}
		datasource.setFilters(conf.getFilters());
    	datasource.setConnectionProperties(conf.getConnectionProperties());
    	return datasource;
    }
    
}