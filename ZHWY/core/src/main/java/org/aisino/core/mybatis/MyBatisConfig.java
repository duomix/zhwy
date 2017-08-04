package org.aisino.core.mybatis;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import com.alibaba.druid.support.http.StatViewServlet;
import com.github.pagehelper.PageHelper;

/**
 * MyBatis基础配置
 * 
 */
@Configuration
@AutoConfigureAfter(DruidDBConfig.class)
@EnableTransactionManagement
@EnableConfigurationProperties(MybatisProperties.class)
public class MyBatisConfig implements TransactionManagementConfigurer {

	private final static Logger logger = LoggerFactory
			.getLogger("Mybatis框架配置：");

	@Autowired
	DataSource dataSource;
	@Autowired
	private MybatisProperties properties;

	/**
	 * 注册DruidServlet 数据源监控配置
	 */
	@Bean
	public ServletRegistrationBean druidServletRegistrationBean() {
		// org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
				new StatViewServlet(), "/druid/*");
		// 添加初始化参数:initParams
		// 白名单:
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		// IP黑名单 (存在共同时,deny优先于allow) : 如果满足deny的话提示:Sorry, you are not
		// permitted to view this page.
		servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
		// 登录查看信息的账号密码.
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "111111");
		// 是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		logger.info(properties.toString());
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setVfs(SpringBootVFS.class);
		factory.setConfiguration(properties.getConfiguration());

		VendorDatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
		Properties properties = new Properties();
		properties.setProperty("MySQL", "mysql");
		properties.setProperty("Oracle", "orcle");
		databaseIdProvider.setProperties(properties);
		factory.setDatabaseIdProvider(databaseIdProvider);

		// 分页插件
		PageHelper pageHelper = new PageHelper();
		Properties pageproperties = new Properties();
		pageproperties.setProperty("reasonable", "true");
		pageproperties.setProperty("supportMethodsArguments", "true");
		pageproperties.setProperty("returnPageInfo", "check");
		properties.setProperty("pageSizeZero", "true");
		pageproperties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);
		// 添加插件
		factory.setPlugins(new Interceptor[] { pageHelper });

		if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
			factory.setTypeAliasesPackage(this.properties
					.getTypeAliasesPackage());
		}
		if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
			factory.setTypeHandlersPackage(this.properties
					.getTypeHandlersPackage());
		}
		if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
			factory.setMapperLocations(this.properties.resolveMapperLocations());
		}
		//设置结果映射
		factory.setTypeAliases(new Class[]{XHashMap.class});
		return factory.getObject();
	}

	/**
	 * 注解事务管理
	 */
	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

}