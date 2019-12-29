package com.o0u0o.house.api.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //声明为Java Config
@ConditionalOnClass({HttpClient.class})
@EnableConfigurationProperties(HttpClientProperties.class)
public class HttpClientAutoConfiguration {
	
	private final HttpClientProperties properties;
	
	public HttpClientAutoConfiguration(HttpClientProperties properties) {
		this.properties = properties;
	}
	
	/**
	 * HttpClient bean定义
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(HttpClient.class)
	public HttpClient httpClient() {
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(properties.getConnectTimeOut())
				.setSocketTimeout(properties.getSocketTimeOut()).build();// 构建requestConfig
		HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
				.setUserAgent(properties.getAgent())
				.setMaxConnPerRoute(properties.getMaxConnPerRoute())
				.setMaxConnTotal(properties.getMaxConnTotal())	//最大链接数技术
				.build();
		return client;
	}
	
}
