package org.aisino.core.redis;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis 工具类
 * 
 * @author XZY 2017-2-7-下午3:58:26
 */
@Component
public class RedisUtils {
	
	/**
	 * 系统初始化数据hashkey
	 */
	public String hash = "aosx";

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 获取hash 中指定列
	 * 
	 * @param hashkey
	 * @param field
	 * @return
	 */
	public Object getHashToValue(String hashkey, String field) {
		return redisTemplate.opsForHash().get(hashkey, field);
	}

	/**
	 * 设置hash 中指定列
	 * 
	 * @param hashkey
	 * @param field
	 * @return
	 * @return
	 */
	public void setHashToValue(String hashkey, String field, Object value) {
		redisTemplate.opsForHash().put(hashkey, field, value);
	}

	/**
	 * 设置hashkey 有效期
	 * 
	 * @param hashkey
	 * @param field
	 * @return
	 * @return
	 */
	public void setHashKeyTimeOut(String hashkey, Long time) {
		redisTemplate.expire(hashkey, time, TimeUnit.SECONDS);
	}

	/**
	 * 删除hash 中指定列
	 * 
	 * @描述:
	 * @方法名: delForHash
	 * @param hashkey
	 * @param sessionids
	 * @返回类型 void
	 * @创建人 XZY
	 * @创建时间 2017-7-6下午2:31:29
	 * @修改人 XZY
	 * @修改时间 2017-7-6下午2:31:29
	 * @修改备注
	 * @since
	 * @throws
	 */
	public void delForHash(String hashkey, String[] keys) {
		if (keys != null && keys.length > 0) {
			for (int i = 0; i < keys.length; i++) {
				redisTemplate.opsForHash().delete(hashkey, keys[i]);
			}
		}
	}

}
