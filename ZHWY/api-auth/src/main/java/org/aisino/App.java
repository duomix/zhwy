package org.aisino;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * api权限服务
 * @author hexing
 */
@SpringBootApplication
@EnableDiscoveryClient
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
