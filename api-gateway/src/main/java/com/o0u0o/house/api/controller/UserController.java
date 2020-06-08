package com.o0u0o.house.api.controller;

import com.o0u0o.house.api.common.ResultMsg;
import com.o0u0o.house.api.common.UserContext;
import com.o0u0o.house.api.model.User;
import com.o0u0o.house.api.service.AccountService;
import com.o0u0o.house.api.service.AgencyService;
import com.o0u0o.house.api.utils.UserHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author aiuiot
 * @Date 2020/1/15 9:59 上午
 * @Descripton: 用户接口
 **/
@Controller
public class UserController {

    @Autowired
    private AccountService accountService;


    /**
     * 忘记密码
     * @param username
     * @param modelMap
     * @return
     */
    @RequestMapping("accounts/remember")
    public String remember(String username,ModelMap modelMap){
        if (StringUtils.isBlank(username)) {
            return "redirect:/accounts/signin?" + ResultMsg.errorMsg("邮箱不能为空").asUrlParams();
        }
        accountService.remember(username);
        modelMap.put("email", username);
        return "/user/accounts/remember";
    }
}
