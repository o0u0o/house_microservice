package com.o0u0o.house.api.dao;

import com.google.common.collect.Lists;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.o0u0o.house.api.common.RestResponse;
import com.o0u0o.house.api.config.GenericRest;
import com.o0u0o.house.api.model.Agency;
import com.o0u0o.house.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author aiuiot
 * @Date 2019/12/29 10:43 下午
 * @Descripton:
 **/
@Repository
public class UserDao {

    /**
     * 服务通信通过GenericRest
     */
    @Autowired
    private GenericRest rest;

    @Value("${user.service.name}")
    private String userServiceName;

    public List<User> getUserList(User query) {
        ResponseEntity<RestResponse<List<User>>> resultEntity = rest.post("http://" + userServiceName + "/user/getList",
                query,
                //反序列化
                new ParameterizedTypeReference<RestResponse<List<User>>>() {
                });

        RestResponse<List<User>> restResponse = resultEntity.getBody();
        if (restResponse.getCode() == 0){
            return restResponse.getResult();
        } else {
            return Lists.newArrayList();    //返回一个空的list
        }
    }


    /**
     * 激活邮箱
     * @param key
     */
    public boolean enable(String key){
        String url = "http://"+ userServiceName +"/user/enable?key=" + key;
        RestResponse<Object> response = rest.get(url, new ParameterizedTypeReference<RestResponse<Object>>() {
        }).getBody();

        return response.getCode() == 0;
    }


    //TODO
    public List<Agency> getAllAgency() {
        return null;
    }

    /**
     * 新增用户
     * @param account
     * @return
     */
    public User addUser(User account) {
        String url = "http://"+ userServiceName +"/user/add";
        ResponseEntity<RestResponse<User>> responseEntity = rest.post(url, account, new ParameterizedTypeReference<RestResponse<User>>() {});
        RestResponse<User> restResponse = responseEntity.getBody();
        if (restResponse.getCode() == 0){
            return restResponse.getResult();
        } else {
            throw new IllegalStateException("Can not add");
        }

    }

    /**
     * TODO
     * 用户鉴权
     * @param user
     * @return
     */
    //@Hystrix
    public User authUser(User user) {
        String url = "http://" + userServiceName + "/user/auth";
        ResponseEntity<RestResponse<User>> responseEntity = rest.post(url, user, new ParameterizedTypeReference<RestResponse<User>>() {});
        RestResponse<User> response = responseEntity.getBody();
        if (response.getCode() == 0){
            return response.getResult();
        } else {
            throw new IllegalStateException("Can not add user");
        }
    }
}
