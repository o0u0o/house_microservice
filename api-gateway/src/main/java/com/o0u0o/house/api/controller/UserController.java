//package com.o0u0o.house.api.controller;
//
//import com.o0u0o.house.api.common.ResultMsg;
//import com.o0u0o.house.api.common.UserContext;
//import com.o0u0o.house.api.model.User;
//import com.o0u0o.house.api.service.AccountService;
//import com.o0u0o.house.api.service.AgencyService;
//import com.o0u0o.house.api.utils.UserHelper;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @Author aiuiot
// * @Date 2020/1/15 9:59 上午
// * @Descripton: 用户接口
// **/
//@Controller
//public class UserController {
//
//    @Autowired
//    private AccountService accountService;
//
//    @Autowired
//    private AgencyService agencyService;
//
//    //----------------------------注册流程-------------------------------------------
//
//    /**
//     * 账号注册
//     * @param account
//     * @param modelMap
//     * @return
//     */
//    @RequestMapping(value="accounts/register",method={RequestMethod.POST,RequestMethod.GET})
//    public String accountsSubmit(User account,ModelMap modelMap){
//        if (account == null || account.getName() == null) {
//            modelMap.put("agencyList",  agencyService.getAllAgency());
//            return "/user/accounts/register";
//        }
//        ResultMsg retMsg =  UserHelper.validate(account);
//
//        if (retMsg.isSuccess() ) {
//            boolean exist = accountService.isExist(account.getEmail());
//            if (!exist) {
//                accountService.addAccount(account);
//                modelMap.put("success_email", account.getEmail());
//                return "/user/accounts/registerSubmit";
//            }else {
//                return "redirect:/accounts/register?" + ResultMsg.errorMsg("邮箱已被占用").asUrlParams();
//            }
//        }else {
//            return "redirect:/accounts/register?" + ResultMsg.errorMsg("参数错误").asUrlParams();
//        }
//    }
//
//    /**
//     * 账号验证
//     * @param key
//     * @return
//     */
//    @RequestMapping("accounts/verify")
//    public String verify(String key){
//        boolean result =  accountService.enable(key);
//        if (result) {
//            return "redirect:/index?" + ResultMsg.successMsg("激活成功").asUrlParams();
//        }else {
//            return "redirect:/accounts/signin?" + ResultMsg.errorMsg("激活失败,请确认链接是否过期").asUrlParams();
//        }
//    }
//
//    //----------------------------登录流程-------------------------------------------
//
//    @RequestMapping(value="/accounts/signin",method={RequestMethod.POST,RequestMethod.GET})
//    public String loginSubmit(HttpServletRequest req){
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        if (username == null || password == null) {
//            req.setAttribute("target", req.getParameter("target"));
//            return "/user/accounts/signin";
//        }
//        User  user =  accountService.auth(username, password);
//        if (user == null) {
//            return "redirect:/accounts/signin?" + "username=" + username + "&" + ResultMsg.errorMsg("用户名或密码错误").asUrlParams();
//        }else {
//            UserContext.setUser(user);
//            return  StringUtils.isNotBlank(req.getParameter("target")) ? "redirect:" + req.getParameter("target") : "redirect:/index";
//        }
//    }
//
//    /**
//     * 忘记密码
//     * @param username
//     * @param modelMap
//     * @return
//     */
//    @RequestMapping("accounts/remember")
//    public String remember(String username,ModelMap modelMap){
//        if (StringUtils.isBlank(username)) {
//            return "redirect:/accounts/signin?" + ResultMsg.errorMsg("邮箱不能为空").asUrlParams();
//        }
//        accountService.remember(username);
//        modelMap.put("email", username);
//        return "/user/accounts/remember";
//    }
//}
