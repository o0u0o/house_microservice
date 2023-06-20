package com.o0u0o.house.user.service;

import com.o0u0o.house.user.common.PageParams;
import com.o0u0o.house.user.mapper.AgencyMapper;
import com.o0u0o.house.user.model.Agency;
import com.o0u0o.house.user.model.User;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.management.resources.agent;

import java.util.List;

/**
 * <h2>经纪人业务类</h2>
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
     * <h2>获取经纪人列表</h2>
     * @return
     */
    public List<Agency> getAllAgency(){
        return agencyMapper.select(new Agency());
    }

    public Agency getAgency(Integer id){
        Agency agency = new Agency();
        agency.setId(id);
        List<Agency> agencies = agencyMapper.select(agency);
        if (agencies.isEmpty()) {
            return null;
        }
        return agencies.get(0);
    }

    /**
     *  <h2>获取经纪人列表-带参数</h2>
     * @param pageParams
     * @return Pair<List<User>, Long>
     */
    public Pair<List<User>, Long> getAllAgent(PageParams pageParams) {
        List<User> agents = agencyMapper.selectAgent(new User(), pageParams);
        setImg(agents);
        //获取经纪人总数 new User()空条件查询
        Long count = agencyMapper.selectAgentCount(new User());
        //组装起来（不可变的Pair）
        return ImmutablePair.of(agents, count);
    }

    /**
     * <h2>添加经纪人</h2>
     * @param agency
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public int add(Agency agency) {
        return  agencyMapper.insert(agency);
    }

    /**
     * <h2>经纪人详情</h2>
     * @param id
     * @return
     */
    public User getAgentDetail(Long id) {
        User user = new User();
        user.setId(id);
        user.setType(2);
        List<User> list = agencyMapper.selectAgent(user, new PageParams(1, 1));
        setImg(list);
        if (!list.isEmpty()) {
            User agent = list.get(0);
            //将经纪人关联的经纪机构也一并查询出来
            Agency agency = new Agency();
            agency.setId(agent.getAgencyId().intValue());
            List<Agency> agencies = agencyMapper.select(agency);
            if (!agencies.isEmpty()) {
                agent.setAgencyName(agencies.get(0).getName());
            }
            return agent;
        }
        return null;
    }

    /**
     * 设置图片
     * @param agents
     */
    private void setImg(List<User> agents) {
        agents.forEach(a ->{
            a.setAvatar(imgPrefix + a.getAvatar());
        });
    }
}
