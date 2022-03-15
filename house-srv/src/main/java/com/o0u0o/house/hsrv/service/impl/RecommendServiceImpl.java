package com.o0u0o.house.hsrv.service.impl;

import com.o0u0o.house.hsrv.common.LimitOffset;
import com.o0u0o.house.hsrv.model.House;
import com.o0u0o.house.hsrv.service.HouseService;
import com.o0u0o.house.hsrv.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1>推荐业务实现类</h1>
 * @author o0u0o
 * @date 2022/3/9 11:34 AM
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    private static final String HOT_HOUSE_KEY = "_hot_house";

    @Autowired
    private HouseService houseService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * <h2>累计热度</h2>
     * @param id
     */
    @Override
    public void increaseHot(long id) {
        //支持分数倒序 当用户点击 增1
        redisTemplate.opsForZSet().incrementScore(HOT_HOUSE_KEY, ""+id, 1.0D);
        // 只保留前10个热门房产，其余的remove(移除)
        redisTemplate.opsForZSet().removeRange(HOT_HOUSE_KEY, 0, -11);
    }

    /**
     * <h2>获取热门房产</h2>
     * @param size 数量
     * @return List<House> 房产列表
     */
    @Override
    public List<House> getHotHouse(Integer size) {
        //bug修复，根据热度从高到底排序
        Set<String> idSet =  redisTemplate.opsForZSet().reverseRange(HOT_HOUSE_KEY, 0, -1);
        List<Long> ids = idSet.stream().map(b -> Long.parseLong(b)).collect(Collectors.toList());
        House query = new House();
        query.setIds(ids);
        return houseService.queryAndSetImg(query, LimitOffset.build(size, 0));
    }

    /**
     * <h2>获取最新房产</h2>
     * @return List<House> 房产列表
     */
    @Override
    public List<House> getLatest() {
        House query = new House();
        query.setSort("create_time");
        return houseService.queryAndSetImg(query, LimitOffset.build(8, 0));
    }
}
