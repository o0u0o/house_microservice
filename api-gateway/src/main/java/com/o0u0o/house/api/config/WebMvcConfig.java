package com.o0u0o.house.api.config;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author aiuiot
 * @Date 2020/1/15 1:15 下午
 * @Descripton:
 **/
@Component
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //配置静态资源映射
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
