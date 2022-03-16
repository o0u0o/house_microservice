package com.o0u0o.house.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <h1>用户服务启动类</h1>
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableSwagger2
public class UserSrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserSrvApplication.class, args);
    }

}
