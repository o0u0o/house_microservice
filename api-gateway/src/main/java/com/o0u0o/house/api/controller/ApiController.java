package com.o0u0o.house.api.controller;

import com.o0u0o.house.api.common.RestResponse;
import com.o0u0o.house.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 触发调用用户服务
 * @Author aiuiot
 * @Date 2019/12/29 10:50 下午
 * @Descripton:
 **/
@RestController
public class ApiController {

    @Autowired
    private UserService userService;

    @RequestMapping("getusrname")
    public RestResponse<String> getusername(Long id){
        return RestResponse.success(userService.getusername(id));
    }
}
