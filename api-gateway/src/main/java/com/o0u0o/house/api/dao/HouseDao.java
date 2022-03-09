package com.o0u0o.house.api.dao;

import com.o0u0o.house.api.common.RestResponse;
import com.o0u0o.house.api.config.GenericRest;
import com.o0u0o.house.api.model.House;
import com.o0u0o.house.api.model.HouseQueryReq;
import com.o0u0o.house.api.model.ListResponse;
import com.o0u0o.house.api.utils.Rests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/4/2 10:49 下午
 * @Descripton: 房产DAO
 **/

@Repository
public class HouseDao {

    @Autowired
    private GenericRest rest;

    @Value("${house.service.name}")
    private String houseServiceName;

    public ListResponse<House> getHouses(House query, Integer limit, Integer offset) {
        RestResponse<ListResponse<House>> resp =  Rests.exc(() ->{
            HouseQueryReq req = new HouseQueryReq();
            req.setLimit(limit);
            req.setOffset(offset);
            req.setQuery(query);
            String url = Rests.toUrl(houseServiceName, "/house/list");
            ResponseEntity<RestResponse<ListResponse<House>>> responseEntity = rest.post(url,req,new ParameterizedTypeReference<RestResponse<ListResponse<House>>>() {});
            return responseEntity.getBody();
        });
        return resp.getResult();
    }


    /**
     * <h2>远程调用最新房产信息</h2>
     * @return
     */
    public List<House> getLastest() {
        RestResponse<List<House>> resp = Rests.exc(() -> {

            String url = Rests.toUrl(houseServiceName, "/house/lastest");
            ResponseEntity<RestResponse<List<House>>> responseEntity = rest.get(url,new ParameterizedTypeReference<RestResponse<List<House>>>() {});
            return responseEntity.getBody();
        });
        return resp.getResult();
    }

    /**
     * <h2>远程调用house服务的热门房产信息</h2>
     * @param recomSize
     * @return List<House> 热门房产列表
     */
    public List<House> getHotHouse(Integer recomSize) {
        return Rests.exc(() ->{
            String url = Rests.toUrl(houseServiceName, "/house/hot" + "?size="+recomSize);
            ResponseEntity<RestResponse<List<House>>> responseEntity =  rest.get(url, new ParameterizedTypeReference<RestResponse<List<House>>>() {});
            return responseEntity.getBody();
        }).getResult();
    }

}
