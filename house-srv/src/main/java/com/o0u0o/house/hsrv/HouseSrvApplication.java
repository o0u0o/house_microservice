package com.o0u0o.house.hsrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <h1>房产服务启动类</h1>
 * @Author o0u0o
 * @Date 2020/4/2 11:17 下午
 * @Descripton: 房产服务启动类
 **/
@SpringBootApplication
@EnableDiscoveryClient
//@EnableSwagger2
public class HouseSrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseSrvApplication.class, args);
    }

}
