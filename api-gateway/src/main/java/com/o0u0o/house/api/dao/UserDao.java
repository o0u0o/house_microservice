package com.o0u0o.house.api.dao;

import com.google.common.collect.Lists;
import com.o0u0o.house.api.common.RestResponse;
import com.o0u0o.house.api.config.GenericRest;
import com.o0u0o.house.api.model.Agency;
import com.o0u0o.house.api.model.ListResponse;
import com.o0u0o.house.api.model.User;
import com.o0u0o.house.api.utils.Rests;
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
            //返回一个空的list
            return Lists.newArrayList();
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

    /**
     * <h2>根据ID获取经纪人</h2>
     * @param id 经纪人ID
     * @return Agency
     */
    public Agency getAgencyById(Integer id) {
        return Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/agency/agencyDetail?id=" + id);
            ResponseEntity<RestResponse<Agency>> responseEntity =
                    rest.get(url, new ParameterizedTypeReference<RestResponse<Agency>>() {});
            return responseEntity.getBody();
        }).getResult();
    }

    /**
     * <h2>新增经纪人</h2>
     * @param agency 经纪人实体
     */
    public void addAgency(Agency agency) {
        Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/agency/add");
            ResponseEntity<RestResponse<Object>> responseEntity =
                    rest.post(url, agency,new ParameterizedTypeReference<RestResponse<Object>>() {});
            return responseEntity.getBody();
        });
    }

    /**
     * <h2>获取经纪人列表</h2>
     * @param limit
     * @param offset
     * @return
     */
    public ListResponse<User> getAgentList(Integer limit, Integer offset) {
        return Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/agency/agentList?limit=" + limit + "&offset="+offset);
            ResponseEntity<RestResponse<ListResponse<User>>> responseEntity =
                    rest.get(url,new ParameterizedTypeReference<RestResponse<ListResponse<User>>>() {});
            return responseEntity.getBody();
        }).getResult();
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

    /**
     * 重置通知
     * @param email
     * @param url
     */
    public void resetNotify(String email, String url) {
        Rests.exc(() -> {
            String sendUrl = Rests.toUrl(userServiceName, "/user/resetNotify?email=" + email + "&url="+url);
            rest.get(sendUrl,new ParameterizedTypeReference<RestResponse<Object>>() {});
            return new Object();
        });
    }
}
