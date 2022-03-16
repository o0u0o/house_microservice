package com.o0u0o.house.api.controller;

import com.o0u0o.house.api.common.ResultMsg;
import com.o0u0o.house.api.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <h1>账号相关控制层</h1>
 * @author o0u0o
 * @date 2022/3/16 2:25 PM
 */
@Controller
@RequestMapping("accounts")
public class AccountController {


    @Autowired
    private AccountService accountService;

    /**
     * <h2>忘记密码</h2>
     * @param username
     * @param modelMap
     * @return
     */
    @RequestMapping("remember")
    public String remember(String username, ModelMap modelMap){
        if (StringUtils.isBlank(username)) {
            return "redirect:/accounts/signin?" + ResultMsg.errorMsg("邮箱不能为空").asUrlParams();
        }
        accountService.remember(username);
        modelMap.put("email", username);
        return "/user/accounts/remember";
    }

}
