package com.o0u0o.house.comment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <h1>HttpClientProperties</h1>
 * @author o0u0o
 * @date 2022/3/16 11:12 AM
 */
@ConfigurationProperties(prefix="spring.httpclient")
public class HttpClientProperties {

    private Integer connectTimeOut = 1000;

    private Integer socketTimeOut = 10000;

    private String agent = "agent";
    private Integer maxConnPerRoute = 10;
    private Integer maxConnTotaol   = 50;
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
    public Integer getMaxConnTotaol() {
        return maxConnTotaol;
    }
    public void setMaxConnTotaol(Integer maxConnTotaol) {
        this.maxConnTotaol = maxConnTotaol;
    }
}
