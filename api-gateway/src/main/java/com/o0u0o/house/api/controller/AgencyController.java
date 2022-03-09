package com.o0u0o.house.api.controller;

import com.google.common.base.Objects;
import com.o0u0o.house.api.common.ResultMsg;
import com.o0u0o.house.api.common.UserContext;
import com.o0u0o.house.api.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <h1>房产经纪人控制层</h1>
 * @author o0u0o
 * @date 2022/3/9 3:35 PM
 */
@Controller
public class AgencyController {

    @RequestMapping("agency/create")
    public String agencyCreate(){
        User user =  UserContext.getUser();
        if (user == null || !Objects.equal(user.getEmail(), "spring_boot@163.com")) {
            return "redirect:/accounts/signin?" + ResultMsg.errorMsg("请先登录").asUrlParams();
        }
        return "/user/agency/create";
    }

}
