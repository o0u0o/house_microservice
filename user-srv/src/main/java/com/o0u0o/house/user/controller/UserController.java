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

    //--------------------- 登录/鉴权 ---------------------

    /**
     * <h2>用户登录</h2>
     * @param user
     * @return
     */
    @RequestMapping("auth")
    public RestResponse<User> login(@RequestBody User user){
        User finalUser = userService.auth(user.getEmail(), user.getPassword());
        return RestResponse.success(finalUser);
    }

    /**
     * <h2>用户鉴权</h2>
     * @param token
     * @return RestResponse
     */
    @RequestMapping("get")
    public RestResponse<User> getUser(String token){
        User finalUser = userService.getLoginedUserByToken(token);
        return RestResponse.success(finalUser);
    }


    /**
     * <h2>登出操作</h2>
     * @param token
     * @return RestResponse<Object>
     */
    @RequestMapping("logout")
    public RestResponse<Object> logout(String token){
        userService.invalidate(token);
        return RestResponse.success();
    }

    /**
     * <h2>用户信息更新</h2>
     * @param user
     * @return RestResponse<User>
     */
    @RequestMapping("update")
    public RestResponse<User> update(@RequestBody User user){
        User updateUser = userService.updateUser(user);
        return RestResponse.success(updateUser);
    }

    @RequestMapping("getKeyEmail")
    public RestResponse<String> getKeyEmail(String key){
        String  email = userService.getResetKeyEmail(key);
        return RestResponse.success(email);
    }

    @RequestMapping("resetNotify")
    public RestResponse<User> resetNotify(String email,String url){
        userService.resetNotify(email,url);
        return RestResponse.success();
    }

}
