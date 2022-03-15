package com.o0u0o.house.hsrv.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.apache.http.client.HttpClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * <h1>Rest自动配置</h1>
 * @author o0u0o
 * @date 2022/3/9 9:27 PM
 */
@Configuration
public class RestAutoConfig {
    public  static class RestTemplateConfig{

        /**
         * 支持负载均衡
         * @param httpClient
         * @return RestTemplate
         */
        @Bean
        @LoadBalanced
        //spring 对rstTmplate bean进行定制 加入loadbalan拦截器进行IP：端口的替换
        RestTemplate lbRestTemplate(HttpClient httpClient){
            RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
            template.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("utf-8")));
            template.getMessageConverters().add(1, new FastJsonHttpMessageConvert5());
            return template;
        }

        /**
         * 直连
         * @param httpClient
         * @return
         */
        @Bean
        RestTemplate directRestTemplate(HttpClient httpClient){
            RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
            template.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("utf-8")));
            template.getMessageConverters().add(1, new FastJsonHttpMessageConvert5());
            return template;
        }

        public static class FastJsonHttpMessageConvert5 extends FastJsonHttpMessageConverter4 {

            static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

            public FastJsonHttpMessageConvert5(){
                setDefaultCharset(DEFAULT_CHARSET);
                setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,new MediaType("application","*+json")));
            }

        }
    }
}
