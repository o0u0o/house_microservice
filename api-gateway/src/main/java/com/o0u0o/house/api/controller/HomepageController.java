package com.o0u0o.house.api.controller;

import com.o0u0o.house.api.model.House;
import com.o0u0o.house.api.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <h1>首页控制层</h1>
 * @Author aiuiot
 * @Date 2020/1/15 11:34 上午
 * @Descripton: 首页
 **/
@Controller
public class HomepageController {

    @Autowired
    private HouseService houseService;

    /**
     * 请求首页
     * @param modelMap
     * @return
     */
    @RequestMapping("index")
    public String accountsRegister(ModelMap modelMap) {
        List<House> houses =  houseService.getLastest();
        modelMap.put("recomHouses", houses);
        return "/homepage/index";
    }

    /**
     * 重定向到请求首页
     * @param modelMap
     * @return
     */
    @RequestMapping("")
    public String index(ModelMap modelMap) {
        return "redirect:/index";
    }
}
