package com.o0u0o.house.api;

import com.o0u0o.house.api.config.NewRuleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <h1>ApiGateway启动类</h1>
 * 注解 @EnableFeignClients 使用feign 时
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@Controller
//@RibbonClient(name = "user", configuration = NewRuleConfig.class)
//@RibbonClient(name = "life-base", configuration = NewRuleConfig.class)
@EnableFeignClients
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("index1")
    @ResponseBody
    public List<ServiceInstance> getRegister(){
        return discoveryClient.getInstances("user");
    }
}
