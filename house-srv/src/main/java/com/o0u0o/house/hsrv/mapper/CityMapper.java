package com.o0u0o.house.hsrv.mapper;

import com.o0u0o.house.hsrv.model.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <h1>城市数据映射对象</h1>
 * @author o0u0o
 * @date 2022/3/9 5:03 PM
 */
@Mapper
public interface CityMapper {

    public List<City> selectCitys(City city);

}
