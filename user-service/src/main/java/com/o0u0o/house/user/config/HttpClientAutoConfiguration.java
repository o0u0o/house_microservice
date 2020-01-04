package com.o0u0o.house.user.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.httpclient.LogbookHttpRequestInterceptor;
import org.zalando.logbook.httpclient.LogbookHttpResponseInterceptor;

@Configuration //声明为Java Config
@ConditionalOnClass({HttpClient.class})
@EnableConfigurationProperties(HttpClientProperties.class)
public class HttpClientAutoConfiguration {
	
	private final HttpClientProperties properties;

	/** 打印请求日志 */
	@Autowired
	private LogbookHttpRequestInterceptor logbookHttpRequestInterceptor;

	/** 打印响应日志*/
	@Autowired
	private LogbookHttpResponseInterceptor logbookHttpResponseInterceptor;
	
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
				.setSocketTimeout(properties.getSocketTimeOut()).build();	// 构建requestConfig
		HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
				.setUserAgent(properties.getAgent())
				.setMaxConnPerRoute(properties.getMaxConnPerRoute())
				.setMaxConnTotal(properties.getMaxConnTotal())			//最大链接数
				.addInterceptorFirst(logbookHttpRequestInterceptor)		//打印请求日志
				.addInterceptorFirst(logbookHttpResponseInterceptor)	//打印响应日志
				.build();
		return client;
	}
	
}
