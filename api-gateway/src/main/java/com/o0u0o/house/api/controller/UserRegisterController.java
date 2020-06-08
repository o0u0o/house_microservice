package com.o0u0o.house.api.controller;

import com.o0u0o.house.api.common.ResultMsg;
import com.o0u0o.house.api.model.User;
import com.o0u0o.house.api.service.AccountService;
import com.o0u0o.house.api.service.AgencyService;
import com.o0u0o.house.api.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author aiuiot
 * @Date 2020/6/8 10:53 下午
 * @Descripton: 用户注册接口
 **/
@Controller
public class UserRegisterController {


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

}
