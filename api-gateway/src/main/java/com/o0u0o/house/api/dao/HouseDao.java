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
 * @Descripton: 房产DAO RestTemplate自定义封装远程调用
 **/

@Repository
public class HouseDao {

    @Autowired
    private GenericRest rest;

    @Value("${house.service.name}")
    private String houseServiceName;

    /**
     * <h1>获取房产(远程调用房产服务)</h1>
     * @param query
     * @param limit
     * @param offset
     * @return
     */
    public ListResponse<House> getHouses(House query, Integer limit, Integer offset) {
        RestResponse<ListResponse<House>> resp =  Rests.exc(() ->{
            HouseQueryReq req = new HouseQueryReq();
            req.setLimit(limit);
            req.setOffset(offset);
            req.setQuery(query);
            //构建url
            String url = Rests.toUrl(houseServiceName, "/house/list");
            //发起调用
            ResponseEntity<RestResponse<ListResponse<House>>> responseEntity = rest.post(url, req, new ParameterizedTypeReference<RestResponse<ListResponse<House>>>() {});
            return responseEntity.getBody();
        });
        return resp.getResult();
    }


    /**
     * <h2>远程调用最新房产信息</h2>
     * @return List<House> 房产列表
     */
    public List<House> getLatest() {
        RestResponse<List<House>> resp = Rests.exc(() -> {
            //服务地址+url
            String url = Rests.toUrl(houseServiceName, "/house/latest");
            ResponseEntity<RestResponse<List<House>>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<List<House>>>() {});
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

    /**
     * <h2>查询单个房产</h2>
     * @param id
     * @return
     */
    public House getOneHouse(Long id){
        return Rests.exc(()->{
            String url = Rests.toUrl(houseServiceName, "/house/detail?id=" + id);
            ResponseEntity<RestResponse<House>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<House>>() {});
            return responseEntity.getBody();
        }).getResult();
    }

    /**
     * <h2>更新评分</h2>
     * @param id
     * @param rating
     */
    public void rating(Long id, Double rating) {
        Rests.exc(() ->{
            String url = Rests.toUrl(houseServiceName, "/house/rating?id=" + id + "&rating=" + rating );
            ResponseEntity<RestResponse<Object>> responseEntity = rest.get(url,new ParameterizedTypeReference<RestResponse<Object>>() {});
            return responseEntity.getBody();
        });
    }
}
