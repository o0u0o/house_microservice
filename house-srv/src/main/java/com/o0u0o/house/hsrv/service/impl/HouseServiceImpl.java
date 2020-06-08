package com.o0u0o.house.hsrv.service.impl;

import com.google.common.collect.Lists;
import com.o0u0o.house.hsrv.common.LimitOffset;
import com.o0u0o.house.hsrv.mapper.HouseMapper;
import com.o0u0o.house.hsrv.model.Community;
import com.o0u0o.house.hsrv.model.House;
import com.o0u0o.house.hsrv.service.HouseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author aiuiot
 * @Date 2020/6/8 6:44 下午
 * @Descripton: 房产服务实现
 **/

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Value("${file.prefix}")
    private String imgPrefix;

    public List<House> queryAndSetImg(House query, LimitOffset pageParams){
        List<House> houses =  houseMapper.selectHouse(query,pageParams);
        houses.forEach(h -> {
            h.setFirstImg(imgPrefix + h.getFirstImg());
            h.setImageList(h.getImageList().stream().map(img -> imgPrefix + img).collect(Collectors.toList()));
            h.setFloorPlanList(h.getFloorPlanList().stream().map(img -> imgPrefix + img).collect(Collectors.toList()));
        });
        return houses;
    }

    @Override
    public Pair<List<House>, Long> queryHouse(House query, LimitOffset build) {
        List<House> houses = Lists.newArrayList();
        House houseQuery = query;
        if (StringUtils.isNoneBlank(query.getName())) {
            Community community = new Community();
            community.setName(query.getName());
            List<Community> communities = houseMapper.selectCommunity(community);
            if (!communities.isEmpty()) {
                houseQuery = new House();
                houseQuery.setCommunityId(communities.get(0).getId());
            }
        }
        houses = queryAndSetImg(houseQuery, build);
        Long count = houseMapper.selectHouseCount(houseQuery);
        return ImmutablePair.of(houses, count);
    }
}
