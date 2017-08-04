package org.aisino.passport.core.init;


import org.aisino.core.redis.RedisUtils;
import org.aisino.passport.sys.service.IAosConstantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 启动加载数据  系统初始化
 * @author XZY
 * 2017-2-8-下午4:50:25
 */
@Component
@Order(value=1)//定义启动顺序
public class StartupRunner implements CommandLineRunner{
	
	@Autowired
	RedisUtils redisUtils;
	
	@Autowired
	IAosConstantsService aosConstantsService;
	
	/**
	 * 具体初始化逻辑
	 */
	@Override
	public void run(String... arg0) {
		System.out.println("############################################");
		System.out.println("####   服务启动执行,执行加载数据 ##############");
		System.out.println("############################################");
	}

}
