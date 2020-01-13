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

    /**
     * 根据用户ID获取用户信息
     * @param id
     * @return
     */
    //@RequestMapping("getById")
    @RequestMapping("user/info")
    public RestResponse<User> getUserById(Long id){
        User user = userService.getUserById(id);
        return RestResponse.success(user);
    }

    /**
     * 根据用户属性获取用户信息
     * @param user
     * @return
     */
    @RequestMapping("getList")
    public RestResponse<List<User>> getList(@RequestBody User user){
        List<User> users = userService.getUserByQuery(user);
        return RestResponse.success(users);
    }

    //--------------------- 用户注册 ---------------------

    /**
     * 注册接口
     * @param user
     * @return
     */
    @RequestMapping("add")
    public RestResponse<User> add(@RequestBody User user){
        userService.addAccount(user, user.getEnableUrl());
        return RestResponse.success();
    }

    /**
     * 激活账号
     * @param key
     * @return
     */
    @RequestMapping("enable")
    public RestResponse<User> enable(String key){
        userService.enable(key);
        return RestResponse.success();
    }


}
