package com.o0u0o.house.api.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;



/**
 * 自动配置
 * @Author aiuiot
 * @Date 2019/12/29 9:29 下午
 * @Descripton:
 **/
@Configuration
public class RestAutoConfig {

    @Bean
    public HttpClient customHttpClient() {
        PoolingHttpClientConnectionManager cm =
                new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);              // ← HC5 用 setMaxTotal
        cm.setDefaultMaxPerRoute(50);     // ← HC5 用 setDefaultMaxPerRoute

        return HttpClients.custom()
                .setConnectionManager(cm)
                .evictIdleConnections(TimeValue.ofSeconds(30))
                .build();
    }

    public  static class RestTemplateConfig{

        /**
         * 支持负载均衡
         * 如果 Spring Cloud 版本支持 RestClient 的负载均衡拦截器，
         * 在这里 .requestInterceptor(...) 配置
         * @return
         */
        @Bean
        public RestClient lbRestClient(RestClient.Builder builder,
                                       LoadBalancerClient loadBalancerClient,
                                       HttpClient customHttpClient) {
            ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(customHttpClient);
            return builder.requestFactory(factory)
                    .requestInterceptor(new LoadBalancerInterceptor(loadBalancerClient))
                    .build();
        }

        /**
         * 直连
         */
        @Bean
        public RestClient directRestClient(RestClient.Builder builder,
                                           HttpClient customHttpClient) {
            ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(customHttpClient);

            return builder
                    .requestFactory(factory)
                    .build();
        }

    }
}
