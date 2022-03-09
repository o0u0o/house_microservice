package com.o0u0o.house.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * <h1>拦截器配置类</h1>
 * @author o0u0o
 * @date 2022/3/9 3:31 PM
 */
@Configuration
public class FilterBeanConfig {

    /**
     * <h2>跨域配置</h2>
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        //放行哪些原始域，比如"http://domain1.com,https://domain2.com"
        config.addAllowedOrigin("*");
        // 放行哪些原始域(头部信息)
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        ////拦截所有的url
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
