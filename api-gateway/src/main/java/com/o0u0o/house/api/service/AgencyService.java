package com.o0u0o.house.api.service;

import com.o0u0o.house.api.dao.UserDao;
import com.o0u0o.house.api.model.Agency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/1/15 10:19 上午
 * @Descripton: 经纪人/经纪机构
 **/
@Service
public class AgencyService {

    @Autowired
    private UserDao userDao;


    /**
     * 获取所有经纪人/机构
     * @return
     */
    public List<Agency> getAllAgency(){
        return userDao.getAllAgency();
    }

    /**
     * <h2>添加经纪人</h2>
     * @param agency
     */
    public void add(Agency agency) {
        userDao.addAgency(agency);
    }

}
