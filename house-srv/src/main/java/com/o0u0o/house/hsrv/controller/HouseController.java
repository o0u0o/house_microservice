package com.o0u0o.house.hsrv.controller;

import com.o0u0o.house.hsrv.common.CommonConstants;
import com.o0u0o.house.hsrv.common.LimitOffset;
import com.o0u0o.house.hsrv.common.RestCode;
import com.o0u0o.house.hsrv.model.UserMsg;
import com.o0u0o.house.hsrv.service.HouseService;
import com.o0u0o.house.hsrv.service.RecommendService;
import org.apache.commons.lang3.tuple.Pair;
import com.o0u0o.house.hsrv.common.RestResponse;
import com.o0u0o.house.hsrv.model.House;
import com.o0u0o.house.hsrv.model.HouseQueryReq;
import com.o0u0o.house.hsrv.model.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/4/2 11:18 下午
 * @Descripton: 房产接口
 **/
@RequestMapping("house")
@RestController
public class HouseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private RecommendService recommendService;

    /**
     * 房产列表
     * @param req
     * @return RestResponse<ListResponse<House>>
     */
    @RequestMapping("list")
    public RestResponse<ListResponse<House>> houseList(@RequestBody HouseQueryReq req){
        Integer limit  = req.getLimit();
        Integer offset = req.getOffset();
        House query  = req.getQuery();
        Pair<List<House>, Long> pair = houseService.queryHouse(query, LimitOffset.build(limit, offset));
        return RestResponse.success(ListResponse.build(pair.getKey(), pair.getValue()));
    }

    @RequestMapping("detail")
    public RestResponse<House> houseDetail(long id){
        House house = houseService.queryOneHouse(id);
        recommendService.increaseHot(id);
        return RestResponse.success(house);
    }

    /**
     * <h1>添加用户留言</h1>
     * @param userMsg
     * @return
     */
//    @RequestMapping("addUserMsg")
//    public RestResponse<Object> houseMsg(@RequestBody UserMsg userMsg){
//        houseService.addUserMsg(userMsg);
//        return RestResponse.success();
//    }

    /**
     * <h2>房产评分</h2>
     * @param rating
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("rating")
    public RestResponse<Object> houseRate(Double rating,Long id){
        houseService.updateRating(id,rating);
        return RestResponse.success();
    }

    /**
     * <h2>房产新增</h2>
     * @param house
     * @return
     */
    @RequestMapping("add")
    public RestResponse<Object> doAdd(@RequestBody House house){
        house.setState(CommonConstants.HOUSE_STATE_UP);
        if (house.getUserId() == null) {
            return RestResponse.error(RestCode.ILLEGAL_PARAMS);
        }
        houseService.addHouse(house,house.getUserId());
        return RestResponse.success();
    }

    /**
     * 热门房产
     * @param size
     * @return
     */
    @RequestMapping("hot")
    public RestResponse<List<House>> getHotHouse(Integer size){
        List<House> list =   recommendService.getHotHouse(size);
        return RestResponse.success(list);
    }

    /**
     * 最新房产
     * @return
     */
    @RequestMapping("lastest")
    public RestResponse<List<House>> getLastest(){
        return RestResponse.success(recommendService.getLastest());
    }
}
