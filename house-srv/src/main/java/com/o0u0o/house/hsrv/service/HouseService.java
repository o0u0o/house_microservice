package com.o0u0o.house.hsrv.service;

import com.google.common.collect.Lists;
import com.o0u0o.house.hsrv.common.LimitOffset;
import com.o0u0o.house.hsrv.model.House;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/6/8 6:37 下午
 * @Descripton: 房产服务接口
 **/
public interface HouseService {

    /**
     * 查询房产
     * @param query
     * @param build
     * @return
     */
    Pair<List<House>, Long> queryHouse(House query, LimitOffset build);

}
