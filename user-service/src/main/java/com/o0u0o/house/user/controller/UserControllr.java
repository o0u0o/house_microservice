package com.o0u0o.house.user.controller;

import com.o0u0o.house.user.common.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author aiuiot
 * @Date 2019/12/29 10:39 下午
 * @Descripton:
 **/
@RestController
public class UserControllr {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllr.class);

    @RequestMapping("getusername")
    public RestResponse<String> getusername(Long id){
        LOGGER.info("用户服务 测试了...");
        return RestResponse.success("test-username");
    }

}
