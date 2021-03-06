package com.o0u0o.house.api.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * Ribbon 配置
 * @Author aiuiot
 * @Date 2019/12/30 11:47 上午
 * @Descripton:
 **/
public class NewRuleConfig {

    @Autowired
    private IClientConfig ribbonClientConfig;

    @Bean
    public IPing ribbonPing(IClientConfig config){
        //指定一个url路径，每次进行健康检查时发送这个url请求
        return new PingUrl(false, "/health");
    }


    /**
     * 负载均衡规则
     * @param config
     * @return
     */
    @Bean
    public IRule ribbonRule(IClientConfig config){
        return new AvailabilityFilteringRule();
    }
}
