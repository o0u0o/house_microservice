package com.o0u0o.house.user;

import com.o0u0o.house.user.config.NewRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * <h1>用户服务启动类</h1>
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableSwagger2
//@RibbonClient(name = "user", configuration = NewRuleConfig.class)
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
