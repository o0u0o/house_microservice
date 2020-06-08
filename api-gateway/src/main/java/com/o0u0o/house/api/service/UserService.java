package com.o0u0o.house.api.service;

import com.o0u0o.house.api.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author aiuiot
 * @Date 2019/12/29 10:49 下午
 * @Descripton: 用户服务
 **/
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

//    public String getusername(Long id){
//        return userDao.getusername(id);
//    }

}
