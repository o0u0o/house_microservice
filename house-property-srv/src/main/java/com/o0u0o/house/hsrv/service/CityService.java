package com.o0u0o.house.hsrv.service;

import com.o0u0o.house.hsrv.model.City;

import java.util.List;

/**
 * <h1>城市业务接口</h1>
 * @author o0u0o
 * @date 2022/3/9 9:41 PM
 */
public interface CityService {

    /**
     * <h2>查询所有城市</h2>
     * @return
     */
    List<City> getAllCitys();

}
