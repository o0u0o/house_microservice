package com.o0u0o.house.api.service;

import com.o0u0o.house.api.common.PageData;
import com.o0u0o.house.api.common.PageParams;
import com.o0u0o.house.api.dao.HouseClient;
import com.o0u0o.house.api.dao.HouseDao;
import com.o0u0o.house.api.model.House;
import com.o0u0o.house.api.model.ListResponse;
import com.o0u0o.house.api.model.UserMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/4/2 9:44 下午
 * @Descripton: 房屋服务
 **/
@Service
public class HouseService {

    @Autowired
    private HouseDao houseDao;

    @Autowired
    private FileService fileService;

    @Autowired
    private HouseClient houseClient;

    /**
     * <h2>更新评分</h2>
     * @param id
     * @param rating
     */
    public void updateRating(Long id, Double rating) {
        houseDao.rating(id, rating);
    }

    /**
     * 添加用户信息
     * @param userMsg
     */
    public void addUserMsg(UserMsg userMsg){
        houseClient.houseMsg(userMsg);
    }

    /**
     * 查询单个房屋信息（使用Feign）
     * @param id 房屋ID
     * @return House
     */
//    public House queryOneHouse(long id){
//        RestResponse<House> response = houseClient.houseDetail(id);
//        return response.getResult();
//    }

    /**
     * 查询单个房屋信息
     * @param id
     * @return
     */
    public House queryOneHouse(Long id){
        return houseDao.getOneHouse(id);
    }

    /**
     * <h2>获取最新房产</h2>
     * @return
     */
    public List<House> getLatest() {
        return houseDao.getLatest();
    }

    /**
     * <h1>查询房产</h1>
     * @param query
     * @param build
     * @return PageData<House>
     */
    public PageData<House> queryHouse(House query, PageParams build) {
        ListResponse<House> result = houseDao.getHouses(query,build.getLimit(),build.getOffset());
        return PageData.<House>buildPage(result.getList(), result.getCount(), build.getPageSize(), build.getPageNum());
    }

    /**
     * <h2>获取热门房产</h2>
     * @param recomSize
     * @return List<House> 房产列表
     */
    public List<House> getHotHouse(Integer recomSize) {
        List<House> list = houseDao.getHotHouse(recomSize);
        return list;
    }

}
