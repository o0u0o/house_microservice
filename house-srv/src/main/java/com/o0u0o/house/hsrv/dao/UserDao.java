package com.o0u0o.house.hsrv.dao;

import com.o0u0o.house.hsrv.common.RestResponse;
import com.o0u0o.house.hsrv.common.utils.Rests;
import com.o0u0o.house.hsrv.model.User;
import com.o0u0o.house.hsrv.service.GenericRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

/**
 * <h1>用户数据访问层</h1>
 * @author o0u0o
 * @date 2022/3/9 9:09 PM
 */

@Repository
public class UserDao {

    @Autowired
    private GenericRest rest;


    @Value("${user.service.name}")
    private String userServiceName;

    public User getAgentDetail(Long agentId) {
        RestResponse<User> response = Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/agency/agentDetail" + "?id=" + agentId);
            ResponseEntity<RestResponse<User>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<User>>() {});
            return responseEntity.getBody();
        });
        return response.getResult();
    }

}
