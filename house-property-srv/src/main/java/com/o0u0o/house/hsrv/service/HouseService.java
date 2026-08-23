package com.o0u0o.house.hsrv.service;

import com.o0u0o.house.hsrv.common.LimitOffset;
import com.o0u0o.house.hsrv.common.enums.HouseUserType;
import com.o0u0o.house.hsrv.model.House;
import com.o0u0o.house.hsrv.model.UserMsg;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * <h1>房产服务接口</h1>
 * @Author aiuiot
 * @Date 2020/6/8 6:37 下午
 * @Descripton: 房产服务接口
 **/
public interface HouseService {

    /**
     * <h2>查询房产</h2>
     * @param query
     * @param build
     * @return
     */
    Pair<List<House>, Long> queryHouse(House query, LimitOffset build);

    /**
     * <h2>查询单个房产</h2>
     * @param id 房产ID
     * @return House 房产对象
     */
    House queryOneHouse(long id);

    /**
     * <h2>新增房产</h2>
     * @param house
     * @param userId
     */
    void addHouse(House house, Long userId);

    /**
     * <h1>绑定用户和房产关系</h1
     * @param id
     * @param userId
     * @param sale
     */
    void bindUser2House(Long id, Long userId,
                        HouseUserType sale);

    /**
     * <h1>解绑</h1>
     * 注意这里逻辑做了修改:当售卖时只能将房产下架，不能解绑用户关系
     *                   当收藏时可以解除用户收藏房产这一关系
     * @param houseId
     * @param userId
     * @param houseUserType
     */
    void unbindUser2Houser(Long houseId,
                           Long userId,
                           HouseUserType houseUserType);

    /**
     * 查询和设置图片
     * @param query
     * @param build
     * @return
     */
    List<House> queryAndSetImg(House query, LimitOffset build);

    /**
     * <h2>向经纪人发送留言通知</h2>
     * @param userMsg
     */
    void addUserMsg(UserMsg userMsg);

    /**
     * <h2>更新评分</h2>
     * @param id
     * @param rating
     */
    void updateRating(Long id, Double rating);
}
