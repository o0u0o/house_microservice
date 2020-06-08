package com.o0u0o.house.api.controller;

import com.o0u0o.house.api.common.PageData;
import com.o0u0o.house.api.common.PageParams;
import com.o0u0o.house.api.service.HouseService;
import com.o0u0o.house.api.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author aiuiot
 * @Date 2020/4/2 10:39 下午
 * @Descripton: 房产接口
 **/
@Controller
public class HouseController {

    @Autowired
    private HouseService houseService;

    /**
     * 房产列表
     * @param pageSize 第几页
     * @param pageNum  每页大小
     * @param query
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "house/list", method = {RequestMethod.POST, RequestMethod.GET})
    public String houseList(Integer pageSize, Integer pageNum, House query, ModelMap modelMap){
        PageData<House> ps = houseService.queryHouse(query, PageParams.build(pageSize, pageNum));
        //List<House> rcHouses =  houseService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", null);
        //modelMap.put("recomHouses", rcHouses);
        modelMap.put("vo", query);
        modelMap.put("ps", ps);
        return "/house/listing";
    }
}
