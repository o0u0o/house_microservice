package com.o0u0o.house.api.dao;

import com.o0u0o.house.api.common.RestResponse;
import com.o0u0o.house.api.config.GenericRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

/**
 * @Author aiuiot
 * @Date 2019/12/29 10:43 下午
 * @Descripton:
 **/
@Repository
public class UserDao {

    @Autowired
    private GenericRest rest;

    public String getusername(Long id){
        //String url = "direct://http://127.0.0.1:8083/getusername?id="+id;
        String url = "http://user/getusername?id="+id;
        RestResponse<String> response = rest.get(url, new ParameterizedTypeReference<RestResponse<String>>() {
        }).getBody();

        return response.getResult();
    }
}
