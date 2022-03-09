package com.o0u0o.house.hsrv.service.impl;

import com.o0u0o.house.hsrv.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>推荐业务实现类</h1>
 * @author o0u0o
 * @date 2022/3/9 11:34 AM
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    private static final String HOT_HOUSE_KEY = "_hot_house";

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 新增热度
     * @param id
     */
    @Override
    public void increaseHot(long id) {
        redisTemplate.opsForZSet().incrementScore(HOT_HOUSE_KEY, ""+id, 1.0D);
        redisTemplate.opsForZSet().removeRange(HOT_HOUSE_KEY, 0, -11);
    }
}
