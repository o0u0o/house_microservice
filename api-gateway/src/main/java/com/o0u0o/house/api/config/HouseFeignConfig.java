package com.o0u0o.house.api.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * <h1>HouseFeign配置</h1>
 * @author o0u0o
 * @date 2022/3/9 4:21 PM
 */
public class HouseFeignConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("user1", "passwd1");
    }
}
