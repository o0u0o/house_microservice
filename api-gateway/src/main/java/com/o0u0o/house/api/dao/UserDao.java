package com.o0u0o.house.api.dao;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
@DefaultProperties(groupKey="userDao",
        commandProperties={@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")},
        threadPoolProperties={@HystrixProperty(name="coreSize",value="10")
                ,@HystrixProperty(name="maxQueueSize",value="1000")},
        threadPoolKey="userDao"
)
public class UserDao {

    /**
     * 服务通信通过GenericRest
     */
    @Autowired
    private GenericRest rest;

    @Value("${user.service.name}")
    private String userServiceName;

    /**
     * <h2>获取用户列表</h2>
     * @param query 查询参数
     * @return List<User> 用户列表
     */
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
     * <h2>新增用户</h2>
     * @param account
     * @return User 用户对象
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
     * 调用鉴权服务
     * @param token
     * @return
     */
    @HystrixCommand(fallbackMethod = "getUserByTokenFb")
    public User getUserByToken(String token) {
        String url = "http://" + userServiceName + "/user/get?token=" + token;
        ResponseEntity<RestResponse<User>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<User>>() {});
        RestResponse<User> response = responseEntity.getBody();
        if (response == null || response.getCode() != 0) {
            return null;
        }
        return response.getResult();
    }

    public User getUserByTokenFb(String token){
        return new User();
    }

    /**
     * <h2>用户鉴权</h2>
     * @param user
     * @return
     */
    @HystrixCommand
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
     * <h2>用户登出</h2>
     * @param token
     */
    @HystrixCommand
    public void logout(String token) {
        String url = "http://" + userServiceName + "/user/logout?token=" + token;
        rest.get(url, new ParameterizedTypeReference<RestResponse<Object>>() {});
    }

    @HystrixCommand
    public List<Agency> getAllAgency() {
        return Rests.exc(() ->{
            String url = Rests.toUrl(userServiceName, "/agency/list");
            ResponseEntity<RestResponse<List<Agency>>> responseEntity =
                    rest.get(url, new ParameterizedTypeReference<RestResponse<List<Agency>>>() {});
            return responseEntity.getBody();
        }).getResult();
    }

    /**
     * 更新用户
     * @param user
     * @return User
     */
    public User updateUser(User user) {
        return Rests.exc(() ->{
            String url = Rests.toUrl(userServiceName, "/user/update");
            ResponseEntity<RestResponse<User>> responseEntity =
                    rest.post(url, user, new ParameterizedTypeReference<RestResponse<User>>() {});
            return responseEntity.getBody();
        }).getResult();
    }

    @HystrixCommand
    public User getAgentById(Long id) {
        return Rests.exc(() ->{
            String url = Rests.toUrl(userServiceName, "/agency/agentDetail?id=" + id);
            ResponseEntity<RestResponse<User>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<User>>() {});
            return responseEntity.getBody();
        }).getResult();
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
     * @return ListResponse<User>
     */
    public ListResponse<User> getAgentList(Integer limit, Integer offset) {
        return Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/agency/agentList?limit=" + limit + "&offset="+offset);
            ResponseEntity<RestResponse<ListResponse<User>>> responseEntity =
                    rest.get(url,new ParameterizedTypeReference<RestResponse<ListResponse<User>>>() {});
            return responseEntity.getBody();
        }).getResult();
    }



    /**
     * <h2>获取邮箱</h2>
     * @param key
     * @return
     */
    public String getEmail(String key) {
        return Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/user/getKeyEmail?key=" + key);
            ResponseEntity<RestResponse<String>> responseEntity =
                    rest.get(url,new ParameterizedTypeReference<RestResponse<String>>() {});
            return responseEntity.getBody();
        }).getResult();
    }

    /**
     * <h2>重置密码</h2>
     * @param key
     * @param password
     * @return User 用户对象
     */
    public User reset(String key, String password) {
        return Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/user/reset?key=" + key + "&password="+password);
            ResponseEntity<RestResponse<User>> responseEntity =
                    rest.get(url,new ParameterizedTypeReference<RestResponse<User>>() {});
            return responseEntity.getBody();
        }).getResult();
    }

    /**
     * 重置通知
     * @param email
     * @param url
     */
    public void resetNotify(String email, String url) {
        Rests.exc(() -> {
            String sendUrl = Rests.toUrl(userServiceName, "/user/resetNotify?email=" + email + "&url="+url);
            rest.get(sendUrl, new ParameterizedTypeReference<RestResponse<Object>>() {});
            return new Object();
        });
    }
}
