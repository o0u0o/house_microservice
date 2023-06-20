package com.o0u0o.house.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * <h1></h1>
 * ClassName: FilterBeanConfig
 * Description:
 *
 * @Author o0u0o
 * @Date 2023/6/20 4:26 PM
 */

@Configuration
public class FilterBeanConfig {
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");//放行哪些原始域，比如"http://domain1.com,https://domain2.com"
        config.addAllowedHeader("*");// 放行哪些原始域(头部信息)
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);////拦截所有的url
        return new CorsFilter(source);
    }
}
