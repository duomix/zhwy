package org.aisino.core.configuration;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * web server 优化相关配置
 * @author XZY
 * 2017-3-27-上午10:11:57
 */
@Configuration
public class WebServerConfiguration {
	@Bean
	public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory() {
		TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
		tomcatFactory.setPort(8080);
		tomcatFactory.addConnectorCustomizers(new MyTomcatConnectorCustomizer());
		return tomcatFactory;
	}
}

class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
	public void customize(Connector connector) {
		Http11NioProtocol protocol = (Http11NioProtocol) connector
				.getProtocolHandler();
		// 设置最大连接数
		protocol.setMaxConnections(2000);
		// 设置最大线程数
		protocol.setMaxThreads(2000);
		// 链接超时
		protocol.setConnectionTimeout(30000);
	}
}
