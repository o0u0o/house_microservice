package com.o0u0o.house.user.controller;

import com.o0u0o.house.user.common.RestResponse;
import com.o0u0o.house.user.exception.IllegalParamsException;
import com.o0u0o.house.user.model.User;
import com.o0u0o.house.user.service.UserService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author aiuiot
 * @Date 2019/12/29 10:39 下午
 * @Descripton:
 **/
@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Value("${server.port}")
    private Integer port;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    //--------------------- 用户查询 ---------------------
    @RequestMapping("getById")
    public RestResponse<User> getUserById(Long id){
        User user = userService.getUserById(id);
        return RestResponse.success(user);
    }

    @RequestMapping("getList")
    public RestResponse<List<User>> getList(@RequestBody User user){
        List<User> users = userService.getUserByQuery(user);
        return RestResponse.success(users);
    }

}
