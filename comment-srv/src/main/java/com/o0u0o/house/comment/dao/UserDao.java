package com.o0u0o.house.comment.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableMap;
import com.o0u0o.house.comment.common.RestResponse;
import com.o0u0o.house.comment.common.Rests;
import com.o0u0o.house.comment.common.Rests.RestFunction;
import com.o0u0o.house.comment.model.User;
import com.o0u0o.house.comment.service.GenericRest;

/**
 * <h1>用户数据访问对象</h1>
 * @author o0u0o
 * @date 2022/3/16 10:51 AM
 */

@Repository
public class UserDao {

    @Autowired
    private GenericRest rest;

    @Value("${user.service.name}")
    private String userServiceName;

    public User getUserDetail(Long userId) {
        RestResponse<User> resp = Rests.exc(new RestFunction<RestResponse<User>>() {

            @Override
            public RestResponse<User> call() throws Exception {
                String url = Rests.toUrl(userServiceName, "/user/getById" + "?id="+userId);
                withParams(ImmutableMap.of("userId", userId));
                ResponseEntity<RestResponse<User>> responseEntity = rest.get(url,new ParameterizedTypeReference<RestResponse<User>>() {});
                return responseEntity.getBody();

            }
        });
        return resp.getResult();
    }

}
