package com.o0u0o.house.hsrv.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author aiuiot
 * @Date 2020/4/2 11:18 下午
 * @Descripton: 房产接口
 **/
@RequestMapping("house")
@RestController
public class HouseController {

    /**
     * 房产列表
     * @param req
     * @return
     */
    @RequestMapping("list")
    public RestResponse<ListResponse<House>> houseList(@RequestBody HouseQueryReq req){
        Integer limit  = req.getLimit();
        Integer offset = req.getOffset();
        House   query  = req.getQuery();
        Pair<List<House>, Long> pair = houseService.queryHouse(query,LimitOffset.build(limit, offset));
        return RestResponse.success(ListResponse.build(pair.getKey(), pair.getValue()));
    }
}
