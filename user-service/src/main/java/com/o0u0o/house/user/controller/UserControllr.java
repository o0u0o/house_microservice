package com.o0u0o.house.user.controller;

import com.o0u0o.house.user.common.RestResponse;
import com.o0u0o.house.user.exception.IllegalParamsException;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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


    @Value("${server.port}")
    private Integer port;

    @RequestMapping("getusername")
    public RestResponse<String> getusername(Long id){
        LOGGER.info("用户服务测试了...+ 端口号为:" + port);
        if (id == null){
            throw new IllegalParamsException(IllegalParamsException.Type.WRONG_TYPE, "错误分页");
        }
        return RestResponse.success("test-username" + port);
    }

}
