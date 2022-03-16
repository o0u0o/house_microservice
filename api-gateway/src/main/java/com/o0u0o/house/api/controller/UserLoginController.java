package com.o0u0o.house.api.controller;

import com.o0u0o.house.api.common.ResultMsg;
import com.o0u0o.house.api.common.UserContext;
import com.o0u0o.house.api.model.User;
import com.o0u0o.house.api.service.AccountService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author aiuiot
 * @Date 2020/6/8 10:51 下午
 * @Descripton: 用户登录接口
 **/
@Controller
public class UserLoginController {

    @Autowired
    private AccountService accountService;

    //------------------------------- 登  录  流  程 S T A R T-------------------------------
    @RequestMapping(value = "/accounts/signin", method = {RequestMethod.POST, RequestMethod.GET})
    public String loginSubmit(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null){
            //获取目标页
            request.setAttribute("target", request.getParameter("target"));
            //返回登录页的模板
            return "/user/accounts/signin";
        }
        User user = accountService.auth(username, password);
        if (user == null){
            return "redirect:/accounts/signin?" + "username" + "&" + ResultMsg.errorMsg("用户名或密码错误").asUrlParams();
        }

        else {
            UserContext.setUser(user);
            return StringUtils.isBlank(request.getParameter("target")) ? "redirect:" + request.getParameter("target") : "redirect:/index";
        }
    }

    /**
     * <h2>账号登出</h2>
     * @param req
     * @return
     */
    @RequestMapping("accounts/logout")
    public String logout(HttpServletRequest req){
        User user = UserContext.getUser();
        accountService.logout(user.getToken());
        return "redirect:/index";
    }
}
