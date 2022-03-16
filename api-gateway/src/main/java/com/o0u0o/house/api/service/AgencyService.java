package com.o0u0o.house.api.service;

import com.o0u0o.house.api.common.PageData;
import com.o0u0o.house.api.common.PageParams;
import com.o0u0o.house.api.dao.UserDao;
import com.o0u0o.house.api.model.Agency;
import com.o0u0o.house.api.model.ListResponse;
import com.o0u0o.house.api.model.User;
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
     * <h2>获取所有经纪人/机构</h2>
     * @return
     */
    public List<Agency> getAllAgency(){
        return userDao.getAllAgency();
    }

    /**
     * <h2>获取经纪人</h2>
     * @param id 经纪人ID
     * @return
     */
    public Agency getAgency(Integer id){
        return userDao.getAgencyById(id);
    }

    /**
     * <h2>添加经纪人</h2>
     * @param agency
     */
    public void add(Agency agency) {
        userDao.addAgency(agency);
    }

    /**
     * <h2>获取所有经纪人</h2>
     * @param pageParams
     * @return PageData<User>
     */
    public PageData<User> getAllAgent(PageParams pageParams) {
        ListResponse<User> result = userDao.getAgentList(pageParams.getLimit(),pageParams.getOffset());
        Long  count = result.getCount();
        return PageData.<User>buildPage(result.getList(), count, pageParams.getPageSize(), pageParams.getPageNum());
    }

    /**
     * <h2>经纪人详情</h2>
     * @param userId
     * @return User
     */
    public User getAgentDetail(Long userId) {
        return userDao.getAgentById(userId);
    }
}
