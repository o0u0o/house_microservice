package com.o0u0o.house.hsrv.service;

import com.o0u0o.house.hsrv.model.House;

import java.util.List;

/**
 * <h1>推荐业务接口</h1>
 * @author o0u0o
 * @date 2022/3/9 11:32 AM
 */
public interface RecommendService {

    /**
     * 新增热度
     * @param id
     */
    void increaseHot(long id);

    /**
     * <h2>获取热门房产</h2>
     * @param size 数量
     * @return List<House> 房产列表
     */
    List<House> getHotHouse(Integer size);

    /**
     * <h2>获取最新房产</h2>
     * @return List<House>
     */
    List<House> getLatest();
}
