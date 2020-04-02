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

    @Autowired
    private AgencyService agencyService;

    //------------------------------- 注  册  流  程 S T A R T-------------------------------
    /**
     * 用户注册
     * @param account
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "accounts/register", method = {RequestMethod.POST, RequestMethod.GET})
    public String accountSubmit(User account, ModelMap modelMap){
        if (account == null || account.getName() == null){
            modelMap.put("agencyList", agencyService.getAllAgency());
            return "/user/accounts/register";
        }
        ResultMsg resultMsg = UserHelper.validate(account);

        if (resultMsg.isSuccess()){
            boolean exist = accountService.isExist(account.getEmail());
            if (!exist){
                accountService.addAccount(account);
                modelMap.put("email", account.getEmail());
                return "/user/accounts/registerSubmit";
            } else {
                return "redirect:/accounts/register?" + resultMsg.asUrlParams();
            }

        } else {
            return "redirect:/accounts/register?" + resultMsg.asUrlParams();
        }

    }

    /**
     * 激活验证
     * @param key
     * @return
     */
    @RequestMapping("accounts/verify")
    public String verify(String key){
        boolean result = accountService.enable(key);
        if (result){
            return "redirect:/index?" + ResultMsg.successMsg("激活成功").asUrlParams();
        } else {
            return "redirect:/accounts/signin?" + ResultMsg.errorMsg("激活失败！请确认链接是否过期");
        }
    }
    //------------------------------- 注  册  流  程 E N D-------------------------------



    //------------------------------- 登  录  流  程 S T A R T-------------------------------
    @RequestMapping(value = "accounts/signin", method = {RequestMethod.POST, RequestMethod.GET})
    public String loginSubmit(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null){
            request.setAttribute("target", request.getParameter("target"));
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
