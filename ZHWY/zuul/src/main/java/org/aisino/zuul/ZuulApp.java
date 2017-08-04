package org.aisino.zuul;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 服务网关
 * 
 * @author XZY 2017-2-17-下午1:38:29
 */
// 这里用了 @SpringCloudApplication 注解，之前没有提过，通过源码我们看到，它整合了 @SpringBootApplication 、
// @EnableDiscoveryClient 、 @EnableCircuitBreaker
@SpringCloudApplication
@EnableFeignClients
@EnableZuulProxy
public class ZuulApp {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ZuulApp.class).web(true).run(args);
	}

	/**
	 * 实例化RestTemplate，通过@LoadBalanced注解开启均衡负载能力.
	 * 
	 * @return restTemplate
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
