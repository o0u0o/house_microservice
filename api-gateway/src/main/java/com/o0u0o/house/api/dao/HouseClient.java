package com.o0u0o.house.api.dao;

import com.o0u0o.house.api.common.RestResponse;
import com.o0u0o.house.api.config.HouseFeignConfig;
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
 * 注解 @FeignClient 将bean 识别为 FeignClient
 * name 为服务名 url 自动将path抽取出来
 *
 **/
//@FeignClient(name = "house", url = "")
@FeignClient(name = "house", configuration = HouseFeignConfig.class)
public interface HouseClient {

    /**
     * 房屋详情
     * 注意 只能写@RequestMapping 而不能写@GetMapping 或者 PostMapping
     * 因为目前FeignClient 之支持@RequestMapping
     * @param id 房屋ID
     * 不能写成GetMapping 或者 PostMapping
     * @return
     */
    @RequestMapping(value = "house/detail", method = RequestMethod.GET)
    public RestResponse<House> houseDetail(@RequestParam("id") Long id);

    /**
     * 添加用户信息
     * @param userMsg 用户消息
     * @return
     */
    @RequestMapping(value = "house/addUserMsg", method = RequestMethod.POST)
    public RestResponse<Object> houseMsg(@RequestBody UserMsg userMsg);



}
