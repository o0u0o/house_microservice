package com.o0u0o.house.hsrv.service.impl;

import com.o0u0o.house.hsrv.mapper.CityMapper;
import com.o0u0o.house.hsrv.model.City;
import com.o0u0o.house.hsrv.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>城市业务实现类</h1>
 * @author o0u0o
 * @date 2022/3/9 9:42 PM
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    /**
     * <h2>查询所有城市</h2>
     * @return List<City> 城市列表
     */
    @Override
    public List<City> getAllCitys() {
        City query = new City();
        return cityMapper.selectCitys(query);
    }
}
