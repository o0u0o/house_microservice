package com.o0u0o.house.api.inteceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO
 * @author o0u0o
 * @date 2022/3/16 4:06 PM
 */
@Configuration
public class WebMvcConf implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private AuthActionInterceptor authActionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .excludePathPatterns("/static/**")
                .addPathPatterns("/**");

        registry.addInterceptor(authActionInterceptor)
                .addPathPatterns(
                        "/house/toAdd",
                        "/accounts/profile",
                        "/accounts/profileSubmit",
                        "/house/bookmarked",
                        "/house/del",
                        "/house/ownlist",
                        "/house/add",
                        "/agency/agentMsg",
                        "/comment/leaveComment",
                        "/comment/leaveBlogComment"
                );
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
