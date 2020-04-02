package com.o0u0o.house.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author aiuiot
 * @Date 2020/1/15 11:34 上午
 * @Descripton: 首页
 **/
@Controller
public class HomepageController {

    /**
     * 请求首页
     * @param modelMap
     * @return
     */
    @RequestMapping("index")
    public String accountsRegister(ModelMap modelMap) {
        return "/homepage/index";
    }

    @RequestMapping("")
    public String index(ModelMap modelMap) {
        return "redirect:/index";
    }
}
