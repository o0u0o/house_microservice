package com.o0u0o.house.user.controller;

import com.o0u0o.house.user.common.PageParams;
import com.o0u0o.house.user.common.RestResponse;
import com.o0u0o.house.user.model.ListResponse;
import com.o0u0o.house.user.model.User;
import com.o0u0o.house.user.service.AgencyService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/1/14 12:11 上午
 * @Descripton: 经纪人接口
 **/
@RestController
@RequestMapping("agency")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    /**
     * 经纪人列表
     * @param limit
     * @param offset
     * @return
     */
    @RequestMapping("agent/list")
    public RestResponse<ListResponse<User>> agentList(Integer limit, Integer offset){
        PageParams pageParams = new PageParams();
        pageParams.setLimit(limit);
        pageParams.setOffset(offset);
        Pair<List<User>, Long> pair =agencyService.getAllAgent(pageParams);
        ListResponse<User> response = ListResponse.build(pair.getKey(), pair.getValue());
        return RestResponse.success(response);
    }

    //经纪人详情
    @RequestMapping("agent/detail")
    public RestResponse<User> agentDetail(Long id){
        User user = agencyService.getAgentDetail(id);
        return RestResponse.success(user);
    }
}
