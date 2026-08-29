package com.o0u0o.house.comment.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * <h1>Rest自动配置类</h1>
 * @author o0u0o
 * @date 2022/3/16 11:12 AM
 */
@Configuration
public class RestAutoConfig {

    @Bean
    public org.apache.hc.client5.http.classic.HttpClient customHttpClient() {
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
         * @param httpClient
         * @return
         */
        @Bean
        @LoadBalanced
        public RestTemplate lbRestTemplate(org.apache.hc.client5.http.classic.HttpClient httpClient) {
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
            return new RestTemplate(factory);
        }

        /**
         * 直连
         * @param httpClient
         * @return
         */
        @Bean
        public RestTemplate directRestTemplate(HttpClient httpClient) {
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
            return new RestTemplate(factory);
        }

    }
}
