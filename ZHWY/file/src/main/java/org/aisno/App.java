package org.aisno;

import javax.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @SpringBootApplication申明让spring boot自动给程序进行必要的配置,等价于以默认属性使用
*  @Configuration,@EnableAutoConfiguration和@ComponentScan
*  @Configuration等价于xml配置beans
*  @ServletComponentScan 注解后，Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册
 * @author root
 */
@SpringBootApplication//默认扫描App类同包以及子包下注解
@ServletComponentScan
@EnableDiscoveryClient//开启发现服务的功能
public class App  extends SpringBootServletInitializer
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
    
    
    /**
	 * 上传文件限制配置
	 * 
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 设置文件大小限制 ,超了,页面会抛出异常信息,这时候就需要进行异常信息的处理了;
		factory.setMaxFileSize("1MB"); // KB,MB
		// 设置总上传数据总大小
		factory.setMaxRequestSize("10MB");// 单文件上传时与MaxFileSize一致多文件上传时总文件大小不能操作此值
		// Sets the directory location where files will be stored.
		factory.setLocation("/");
		return factory.createMultipartConfig();
	}
    
	
}
