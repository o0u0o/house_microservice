package com.o0u0o.house.api.dao;

import com.o0u0o.house.api.common.RestResponse;
import com.o0u0o.house.api.model.House;
import com.o0u0o.house.api.model.UserMsg;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author aiuiot
 * @Date 2020/1/5 11:20 上午
 * @Descripton: 房产客户端
 **/
//@FeignClient(name = "house", url = "")
@FeignClient(name = "house")
public interface HouseClient {

    /**
     * 房屋详情
     * 注意 只能写@RequestMapping 而不能写@GetMapping 或者 PostMapping
     * 因为目前FeignClient 之支持@RequestMapping
     * @param id 房屋ID
     * @return
     */
    @RequestMapping(value = "house/detail", method = RequestMethod.GET)
    public RestResponse<House> houseDetail(@RequestParam("id") Long id);

    @RequestMapping(value = "house/addUserMsg", method = RequestMethod.POST)
    public RestResponse<Object> houseMsg(@RequestBody UserMsg userMsg);



}
