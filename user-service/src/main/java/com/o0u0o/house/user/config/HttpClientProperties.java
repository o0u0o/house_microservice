package com.o0u0o.house.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.httpclient")
public class HttpClientProperties {

	//链接超时时间 1s
	private Integer connectTimeOut = 1000;

	//读超时 10秒
	private Integer socketTimeOut = 10000;
	
	private String agent = "agent";
	// 每个IP的自带连接数
	private Integer maxConnPerRoute = 10;
	// 总连接数
	private Integer maxConnTotal = 50;
	public Integer getConnectTimeOut() {
		return connectTimeOut;
	}
	public void setConnectTimeOut(Integer connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}
	public Integer getSocketTimeOut() {
		return socketTimeOut;
	}
	public void setSocketTimeOut(Integer socketTimeOut) {
		this.socketTimeOut = socketTimeOut;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public Integer getMaxConnPerRoute() {
		return maxConnPerRoute;
	}
	public void setMaxConnPerRoute(Integer maxConnPerRoute) {
		this.maxConnPerRoute = maxConnPerRoute;
	}
	public Integer getMaxConnTotal() {
		return maxConnTotal;
	}
	public void setMaxConnTotal(Integer maxConnTotal) {
		this.maxConnTotal = maxConnTotal;
	}
	
	
}
