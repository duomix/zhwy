package org.aisino.core.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Lazy(false)
public class RestClient {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RestClient.class);
	private static RestTemplate restTemplate;
	
	/**
	 * 静态区配置RestTemplate
	 */
	static{
		
		restTemplate = new RestTemplate();
		LOGGER.info("RestClient初始化完成");
	}
	
	/**
	 * 实例化RestTemplate,通过@LoadBalanced注解开启均衡负载能力.
	 * 
	 * @return restTemplate
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return restTemplate;
	}

	private RestClient() {
	}
	
}
