package org.aisino.core.mybatis;

/**
 * 对应MyBatis XML  配置中的  settings
 * @author hexing
 *
 */
public class MyBatisSettings {
		/**
		 * 该配置影响的所有映射器中配置的缓存的全局开关
		 * */
		private   boolean    cacheEnabled = true;
		
		/**
		 * 延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。 
		 * 特定关联关系中可通过设置fetchType属性来覆盖该项的开关状态
		 */
		private  boolean    lazyLoadingEnabled = false;
		
		/**
		 * 当启用时，对任意延迟属性的调用会使带有延迟加载属性的对象完整加载；
		 * 反之，每种属性将会按需加载
		 */
		private boolean   aggressiveLazyLoading = true;
		
		/**
		 * 是否允许单一语句返回多结果集（需要兼容驱动）
		 */
		private  boolean  multipleResultSetsEnabled = true;
		
		/**
		 * 使用列标签代替列名。不同的驱动在这方面会有不同的表现， 
		 * 具体可参考相关驱动文档或通过测试这两种不同的模式来观察所用驱动的结果
		 */
		private  boolean  useColumnLabel = true;
		
		/**
		 * 允许 JDBC 支持自动生成主键，需要驱动兼容。 
		 * 如果设置为 true 则这个设置强制使用自动生成主键，尽管一些驱动不能兼容但仍可正常工作（比如 Derby）
		 */
		private  boolean  useGeneratedKeys = false;
		
		/**
		 * 	指定 MyBatis 应如何自动映射列到字段或属性。 
		 * 	NONE 表示取消自动映射；
		 * 	PARTIAL 只会自动映射没有定义嵌套结果集映射的结果集。
		 * 	 FULL 会自动映射任意复杂的结果集（无论是否嵌套
		 */
		private  String   autoMappingBehavior = "PARTIAL";
		
		/**
		 * Specify the behavior when detects an unknown column (or unknown property type) of automatic mapping target.
			NONE: Do nothing
			WARNING: Output warning log (The log level of 'org.apache.ibatis.session.AutoMappingUnknownColumnBehavior' must be set to WARN)
			FAILING: Fail mapping (Throw SqlSessionException)
		 */
		private   String  autoMappingUnknownColumnBehavior = "NONE";
		
		/**
		 * 配置默认的执行器。
		 * SIMPLE 就是普通的执行器；
		 * REUSE 执行器会重用预处理语句（prepared statements）； 
		 * BATCH 执行器将重用语句并执行批量更新。
		 */
		private  String  defaultExecutorType = "SIMPLE";
		
		/**
		 * 设置超时时间，它决定驱动等待数据库响应的秒数
		 */
		private   Integer    defaultStatementTimeout;
		
		/**
		 * 	Sets the driver a hint as to control fetching size for return results. This parameter value can be override by a query setting.
		 */
		private  Integer    defaultFetchSize;
		
		/**
		 * 允许在嵌套语句中使用分页（RowBounds）。 If allow, set the false.
		 */
		private  boolean  safeRowBoundsEnabled=false;
		
		/**
		 * 允许在嵌套语句中使用分页（ResultHandler）。 If allow, set the false.
		 */
		private  boolean  safeResultHandlerEnabled = false;
		
		/**
		 * 是否开启自动驼峰命名规则（camel case）映射，即从经典数据库列名 A_COLUMN 到经典 Java 属性名 aColumn 的类似映射。
		 */
		private  boolean  mapUnderscoreToCamelCase = false;
		
		/**
		 * MyBatis 利用本地缓存机制（Local Cache）防止循环引用（circular references）和加速重复嵌套查询。
		 *  默认值为 SESSION，这种情况下会缓存一个会话中执行的所有查询。
		 *   若设置值为 STATEMENT，本地会话仅用在语句执行上，对相同 SqlSession 的不同调用将不会共享数据。
		 */
		private  String   localCacheScope = "SESSION";
		
		/**
		 * 当没有为参数提供特定的 JDBC 类型时，为空值指定 JDBC 类型。
		 *  某些驱动需要指定列的 JDBC 类型，多数情况直接用一般类型即可，比如 NULL、VARCHAR 或 OTHER。
		 */
		private  String  jdbcTypeForNull = "OTHER";
		
		/**
		 * 指定哪个对象的方法触发一次延迟加载。
		 * A method name list separated by commas	
		 */
		private  String  lazyLoadTriggerMethods = "equals,clone,hashCode,toString";
		
		/**
		 * 指定动态 SQL 生成的默认语言。
		 * A type alias or fully qualified class name.
		 */
		private  String   defaultScriptingLanguage;
		
		/**
		 * 指定当结果集中值为 null 的时候是否调用映射对象的 setter（map 对象时为 put）方法，
		 * 这对于有 Map.keySet() 依赖或 null 值初始化的时候是有用的。
		 * 注意基本类型（int、boolean等）是不能设置成 null 的。
		 */
		private  boolean  callSettersOnNulls = false;
		
		/**
		 * 指定 MyBatis 增加到日志名称的前缀。
		 */
		private   String   logPrefix;
		
		/**
		 * 指定 MyBatis 所用日志的具体实现，未指定时将自动查找。
		 * SLF4J | LOG4J | LOG4J2 | JDK_LOGGING | COMMONS_LOGGING | STDOUT_LOGGING | NO_LOGGING
		 */
		private  String   logImpl;
		
		/**
		 * 指定 Mybatis 创建具有延迟加载能力的对象所用到的代理工具。	
		 *  CGLIB | JAVASSIST
		 */
		private  String  proxyFactory="JAVASSIST";
		
		/**
		 * pecifies VFS implementations	Fully qualified class names of custom VFS implementation separated by commas.
		 */
		private  String  vfsImpl;
		
		/**
		 * Allow referencing statement parameters by their actual names declared in the method signature. 
		 * To use this feature, your project must be compiled in Java 8 with -parameters option. (Since: 3.4.1)
		 */
		private  boolean  useActualParamName=true;

		public boolean isCacheEnabled() {
			return cacheEnabled;
		}

		public void setCacheEnabled(boolean cacheEnabled) {
			this.cacheEnabled = cacheEnabled;
		}

		public boolean isLazyLoadingEnabled() {
			return lazyLoadingEnabled;
		}

		public void setLazyLoadingEnabled(boolean lazyLoadingEnabled) {
			this.lazyLoadingEnabled = lazyLoadingEnabled;
		}

		public boolean isAggressiveLazyLoading() {
			return aggressiveLazyLoading;
		}

		public void setAggressiveLazyLoading(boolean aggressiveLazyLoading) {
			this.aggressiveLazyLoading = aggressiveLazyLoading;
		}

		public boolean isMultipleResultSetsEnabled() {
			return multipleResultSetsEnabled;
		}

		public void setMultipleResultSetsEnabled(boolean multipleResultSetsEnabled) {
			this.multipleResultSetsEnabled = multipleResultSetsEnabled;
		}

		public boolean isUseColumnLabel() {
			return useColumnLabel;
		}

		public void setUseColumnLabel(boolean useColumnLabel) {
			this.useColumnLabel = useColumnLabel;
		}

		public boolean isUseGeneratedKeys() {
			return useGeneratedKeys;
		}

		public void setUseGeneratedKeys(boolean useGeneratedKeys) {
			this.useGeneratedKeys = useGeneratedKeys;
		}

		public String getAutoMappingBehavior() {
			return autoMappingBehavior;
		}

		public void setAutoMappingBehavior(String autoMappingBehavior) {
			this.autoMappingBehavior = autoMappingBehavior;
		}

		public String getAutoMappingUnknownColumnBehavior() {
			return autoMappingUnknownColumnBehavior;
		}

		public void setAutoMappingUnknownColumnBehavior(String autoMappingUnknownColumnBehavior) {
			this.autoMappingUnknownColumnBehavior = autoMappingUnknownColumnBehavior;
		}

		public String getDefaultExecutorType() {
			return defaultExecutorType;
		}

		public void setDefaultExecutorType(String defaultExecutorType) {
			this.defaultExecutorType = defaultExecutorType;
		}

		public Integer getDefaultStatementTimeout() {
			return defaultStatementTimeout;
		}

		public void setDefaultStatementTimeout(Integer defaultStatementTimeout) {
			this.defaultStatementTimeout = defaultStatementTimeout;
		}

		public Integer getDefaultFetchSize() {
			return defaultFetchSize;
		}

		public void setDefaultFetchSize(Integer defaultFetchSize) {
			this.defaultFetchSize = defaultFetchSize;
		}

		public boolean isSafeRowBoundsEnabled() {
			return safeRowBoundsEnabled;
		}

		public void setSafeRowBoundsEnabled(boolean safeRowBoundsEnabled) {
			this.safeRowBoundsEnabled = safeRowBoundsEnabled;
		}

		public boolean isSafeResultHandlerEnabled() {
			return safeResultHandlerEnabled;
		}

		public void setSafeResultHandlerEnabled(boolean safeResultHandlerEnabled) {
			this.safeResultHandlerEnabled = safeResultHandlerEnabled;
		}

		public boolean isMapUnderscoreToCamelCase() {
			return mapUnderscoreToCamelCase;
		}

		public void setMapUnderscoreToCamelCase(boolean mapUnderscoreToCamelCase) {
			this.mapUnderscoreToCamelCase = mapUnderscoreToCamelCase;
		}

		public String getLocalCacheScope() {
			return localCacheScope;
		}

		public void setLocalCacheScope(String localCacheScope) {
			this.localCacheScope = localCacheScope;
		}

		public String getJdbcTypeForNull() {
			return jdbcTypeForNull;
		}

		public void setJdbcTypeForNull(String jdbcTypeForNull) {
			this.jdbcTypeForNull = jdbcTypeForNull;
		}

		public String getLazyLoadTriggerMethods() {
			return lazyLoadTriggerMethods;
		}

		public void setLazyLoadTriggerMethods(String lazyLoadTriggerMethods) {
			this.lazyLoadTriggerMethods = lazyLoadTriggerMethods;
		}

		public String getDefaultScriptingLanguage() {
			return defaultScriptingLanguage;
		}

		public void setDefaultScriptingLanguage(String defaultScriptingLanguage) {
			this.defaultScriptingLanguage = defaultScriptingLanguage;
		}

		public boolean isCallSettersOnNulls() {
			return callSettersOnNulls;
		}

		public void setCallSettersOnNulls(boolean callSettersOnNulls) {
			this.callSettersOnNulls = callSettersOnNulls;
		}

		public String getLogPrefix() {
			return logPrefix;
		}

		public void setLogPrefix(String logPrefix) {
			this.logPrefix = logPrefix;
		}

		public String getLogImpl() {
			return logImpl;
		}

		public void setLogImpl(String logImpl) {
			this.logImpl = logImpl;
		}

		public String getProxyFactory() {
			return proxyFactory;
		}

		public void setProxyFactory(String proxyFactory) {
			this.proxyFactory = proxyFactory;
		}

		public String getVfsImpl() {
			return vfsImpl;
		}

		public void setVfsImpl(String vfsImpl) {
			this.vfsImpl = vfsImpl;
		}

		public boolean isUseActualParamName() {
			return useActualParamName;
		}

		public void setUseActualParamName(boolean useActualParamName) {
			this.useActualParamName = useActualParamName;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("MyBatisSettings [cacheEnabled=");
			builder.append(cacheEnabled);
			builder.append(", lazyLoadingEnabled=");
			builder.append(lazyLoadingEnabled);
			builder.append(", aggressiveLazyLoading=");
			builder.append(aggressiveLazyLoading);
			builder.append(", multipleResultSetsEnabled=");
			builder.append(multipleResultSetsEnabled);
			builder.append(", useColumnLabel=");
			builder.append(useColumnLabel);
			builder.append(", useGeneratedKeys=");
			builder.append(useGeneratedKeys);
			builder.append(", autoMappingBehavior=");
			builder.append(autoMappingBehavior);
			builder.append(", autoMappingUnknownColumnBehavior=");
			builder.append(autoMappingUnknownColumnBehavior);
			builder.append(", defaultExecutorType=");
			builder.append(defaultExecutorType);
			builder.append(", defaultStatementTimeout=");
			builder.append(defaultStatementTimeout);
			builder.append(", defaultFetchSize=");
			builder.append(defaultFetchSize);
			builder.append(", safeRowBoundsEnabled=");
			builder.append(safeRowBoundsEnabled);
			builder.append(", safeResultHandlerEnabled=");
			builder.append(safeResultHandlerEnabled);
			builder.append(", mapUnderscoreToCamelCase=");
			builder.append(mapUnderscoreToCamelCase);
			builder.append(", localCacheScope=");
			builder.append(localCacheScope);
			builder.append(", jdbcTypeForNull=");
			builder.append(jdbcTypeForNull);
			builder.append(", lazyLoadTriggerMethods=");
			builder.append(lazyLoadTriggerMethods);
			builder.append(", defaultScriptingLanguage=");
			builder.append(defaultScriptingLanguage);
			builder.append(", callSettersOnNulls=");
			builder.append(callSettersOnNulls);
			builder.append(", logPrefix=");
			builder.append(logPrefix);
			builder.append(", logImpl=");
			builder.append(logImpl);
			builder.append(", proxyFactory=");
			builder.append(proxyFactory);
			builder.append(", vfsImpl=");
			builder.append(vfsImpl);
			builder.append(", useActualParamName=");
			builder.append(useActualParamName);
			builder.append("]");
			return builder.toString();
		}
	
}