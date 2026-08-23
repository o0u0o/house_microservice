package com.o0u0o.house.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <h1>评论服务启动类</h1>
 * @author o0u0o
 * @date 2022/3/9 10:25 PM
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CommentSrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentSrvApplication.class, args);
    }

}
