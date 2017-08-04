package org.aisino.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册中心
 * 使用Eureka做服务发现。
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApp 
{
	public static void main(String[] args) {
		SpringApplication.run(EurekaApp.class, args);
	}
}
