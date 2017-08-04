package org.aisino.core.mybatis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.executor.loader.ProxyFactory;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.AutoMappingUnknownColumnBehavior;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.type.JdbcType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * 配置Mybatis
 */
@ConfigurationProperties(prefix = MybatisProperties.MYBATIS_PREFIX)
public class MybatisProperties {

	public static final String MYBATIS_PREFIX = "mybatis";
	
	private  MyBatisSettings settings;
	
	private  String  daoPackage;

	/**
	 * Config file path.
	 */
	private String configLocation;

	/**
	 * Location of mybatis mapper files.
	 */
	private String[] mapperLocations;

	/**
	 * Package to scan domain objects.
	 */
	private String typeAliasesPackage;

	/**
	 * Package to scan handlers.
	 */
	private String typeHandlersPackage;

	/**
	 * Check the config file exists.
	 */
	private boolean checkConfigLocation = false;

	/**
	 * Execution mode for {@link org.mybatis.spring.SqlSessionTemplate}.
	 */
	private ExecutorType executorType;

	/**
	 * A Configuration object for customize default settings. If
	 * {@link #configLocation} is specified, this property is not used.
	 */
	private Configuration configuration = new Configuration();
	
	

	public MyBatisSettings getSettings() {
		return settings;
	}

	public void setSettings(MyBatisSettings settings) {
		this.settings = settings;
	}

	/**
	 * @since 1.1.0
	 * @return
	 */
	public String getConfigLocation() {
		return this.configLocation;
	}

	/**
	 * @since 1.1.0
	 * @return
	 */
	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

	public String[] getMapperLocations() {
		return this.mapperLocations;
	}

	public void setMapperLocations(String[] mapperLocations) {
		this.mapperLocations = mapperLocations;
	}

	public String getTypeHandlersPackage() {
		return this.typeHandlersPackage;
	}

	public void setTypeHandlersPackage(String typeHandlersPackage) {
		this.typeHandlersPackage = typeHandlersPackage;
	}

	public String getTypeAliasesPackage() {
		return this.typeAliasesPackage;
	}

	public void setTypeAliasesPackage(String typeAliasesPackage) {
		this.typeAliasesPackage = typeAliasesPackage;
	}

	public boolean isCheckConfigLocation() {
		return this.checkConfigLocation;
	}

	public void setCheckConfigLocation(boolean checkConfigLocation) {
		this.checkConfigLocation = checkConfigLocation;
	}

	public ExecutorType getExecutorType() {
		return this.executorType;
	}

	public void setExecutorType(ExecutorType executorType) {
		this.executorType = executorType;
	}

	public Configuration getConfiguration() {
			configuration.setAutoMappingBehavior(AutoMappingBehavior.valueOf(settings.getAutoMappingBehavior()));
		    configuration.setAutoMappingUnknownColumnBehavior(AutoMappingUnknownColumnBehavior.valueOf(settings.getAutoMappingUnknownColumnBehavior()));
		    configuration.setCacheEnabled(settings.isCacheEnabled());
		    configuration.setProxyFactory((ProxyFactory) createInstance(settings.getProxyFactory()));
		    configuration.setLazyLoadingEnabled(settings.isLazyLoadingEnabled());
		    configuration.setAggressiveLazyLoading(settings.isAggressiveLazyLoading());
		    configuration.setMultipleResultSetsEnabled(settings.isMultipleResultSetsEnabled());
		    configuration.setUseColumnLabel(settings.isUseColumnLabel());
		    configuration.setUseGeneratedKeys(settings.isUseGeneratedKeys());
		    configuration.setDefaultExecutorType(ExecutorType.valueOf(settings.getDefaultExecutorType()));
		    configuration.setDefaultStatementTimeout(settings.getDefaultStatementTimeout());
		    configuration.setDefaultFetchSize(settings.getDefaultFetchSize());
		    configuration.setMapUnderscoreToCamelCase(settings.isMapUnderscoreToCamelCase());
		    configuration.setSafeRowBoundsEnabled(settings.isSafeRowBoundsEnabled());
		    configuration.setLocalCacheScope(LocalCacheScope.valueOf(settings.getLocalCacheScope()));
		    configuration.setJdbcTypeForNull(JdbcType.valueOf(settings.getJdbcTypeForNull()));
		    configuration.setLazyLoadTriggerMethods(stringSetValueOf(settings.getLazyLoadTriggerMethods(),null));
		    configuration.setSafeResultHandlerEnabled(settings.isSafeResultHandlerEnabled());
		    configuration.setDefaultScriptingLanguage(resolveClass(settings.getDefaultScriptingLanguage()));
		    configuration.setCallSettersOnNulls(settings.isCallSettersOnNulls());
		    configuration.setUseActualParamName(settings.isUseActualParamName());
		    configuration.setLogPrefix(settings.getLogPrefix());
		    @SuppressWarnings("unchecked")
		    Class<? extends Log> logImpl = (Class<? extends Log>)resolveClass(settings.getLogImpl());
		    configuration.setLogImpl(logImpl);
		    return configuration;
	}

	public Resource[] resolveMapperLocations() {
		List<Resource> resources = new ArrayList<Resource>();
		if (this.mapperLocations != null) {
			for (String mapperLocation : this.mapperLocations) {
				Resource[] mappers;
				try {
					mappers = new PathMatchingResourcePatternResolver().getResources(mapperLocation);
					resources.addAll(Arrays.asList(mappers));
				} catch (IOException e) {

				}
			}
		}

		Resource[] mapperLocations = new Resource[resources.size()];
		mapperLocations = resources.toArray(mapperLocations);
		return mapperLocations;
	}
	
	 private Set<String> stringSetValueOf(String value, String defaultValue) {
		    value = (value == null ? defaultValue : value);
		    return new HashSet<String>(Arrays.asList(value.split(",")));
	}
	 

	  protected Object createInstance(String alias) {
	    Class<?> clazz = resolveClass(alias);
	    if (clazz == null) {
	      return null;
	    }
	    try {
	      return resolveClass(alias).newInstance();
	    } catch (Exception e) {
	      throw new BuilderException("Error creating instance. Cause: " + e, e);
	    }
	  }

	  protected Class<?> resolveClass(String alias) {
	    if (alias == null) {
	      return null;
	    }
	    try {
	      return resolveAlias(alias);
	    } catch (Exception e) {
	      throw new BuilderException("Error resolving class. Cause: " + e, e);
	    }
	  }
	  
	  protected Class<?> resolveAlias(String alias) {
		    return this.configuration.getTypeAliasRegistry().resolveAlias(alias);
	 }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MybatisProperties [settings=");
		builder.append(settings);
		builder.append(", mapperLocations=");
		builder.append(Arrays.toString(mapperLocations));
		builder.append(", typeAliasesPackage=");
		builder.append(typeAliasesPackage);
		builder.append(", typeHandlersPackage=");
		builder.append(typeHandlersPackage);
		builder.append("]");
		return builder.toString();
	}

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}
	  
}