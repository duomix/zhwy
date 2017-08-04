package org.aisino.core.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * 分布式Session状态保存Redis
 * Session 中所有实体类实现 Serializable 接口
 * @author root
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800) //30分钟失效
public class RedisSessionConfig {
}
