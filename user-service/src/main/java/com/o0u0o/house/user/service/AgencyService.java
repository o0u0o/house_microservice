package com.o0u0o.house.user.service;

import com.o0u0o.house.user.common.PageParams;
import com.o0u0o.house.user.mapper.AgencyMapper;
import com.o0u0o.house.user.model.User;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/1/14 12:28 上午
 * @Descripton: 经纪人服务
 **/
@Service
public class AgencyService {

    @Value("${file.prefix}")
    private String imgPrefix;

    @Autowired
    private AgencyMapper agencyMapper;

    /**
     * 获取经纪人列表
     * @param pageParams
     * @return
     */
    public Pair<List<User>, Long> getAllAgent(PageParams pageParams) {
        List<User> agents = agencyMapper.selectAgent(new User(), pageParams);
        setImg(agents);
        //获取经纪人总数
        Long count = agencyMapper.selectAgentCount(new User()); //new User()空条件查询
        return ImmutablePair.of(agents, count); //组装起来（不可变的Pair）
    }

    //设置图片
    private void setImg(List<User> agents) {
     agents.forEach(a ->{
         a.setAvatar(imgPrefix + a.getAvatar());
     });
    }

    public User getAgentDetail(Long id) {
        User user = new User();
        user.setId(id);
        user.setType(2);
        List<User> list = agencyMapper.selectAgent(user, new PageParams(1, 1));
        if (list.isEmpty()){
            return null;
        }
        return list.get(0);
    }


}
