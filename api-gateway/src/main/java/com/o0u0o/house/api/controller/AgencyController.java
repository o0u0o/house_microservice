package com.o0u0o.house.api.controller;

import com.google.common.base.Objects;
import com.o0u0o.house.api.common.ResultMsg;
import com.o0u0o.house.api.common.UserContext;
import com.o0u0o.house.api.model.Agency;
import com.o0u0o.house.api.model.User;
import com.o0u0o.house.api.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <h1>房产经纪人控制层</h1>
 * @author o0u0o
 * @date 2022/3/9 3:35 PM
 */
@Controller
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @RequestMapping("agency/create")
    public String agencyCreate(){
        User user =  UserContext.getUser();
        if (user == null || !Objects.equal(user.getEmail(), "spring_boot@163.com")) {
            return "redirect:/accounts/signin?" + ResultMsg.errorMsg("请先登录").asUrlParams();
        }
        return "/user/agency/create";
    }

    @RequestMapping("agency/submit")
    public String agencySubmit(Agency agency){
        User user =  UserContext.getUser();
        if (user == null || !Objects.equal(user.getEmail(), "spring_boot@163.com")) {
            return "redirect:/accounts/signin?" + ResultMsg.errorMsg("请先登录").asUrlParams();
        }
        agencyService.add(agency);
        return "redirect:/index?" + ResultMsg.errorMsg("创建成功").asUrlParams();
    }

}
