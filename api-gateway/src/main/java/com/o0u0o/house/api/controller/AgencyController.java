package com.o0u0o.house.api.controller;

import com.google.common.base.Objects;
import com.o0u0o.house.api.common.*;
import com.o0u0o.house.api.model.Agency;
import com.o0u0o.house.api.model.House;
import com.o0u0o.house.api.model.User;
import com.o0u0o.house.api.service.AgencyService;
import com.o0u0o.house.api.service.HouseService;
import com.o0u0o.house.api.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <h1>房产经纪人控制层</h1>
 * @author o0u0o
 * @date 2022/3/9 3:35 PM
 */
@Controller
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private MailService mailService;

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

    @RequestMapping("agency/list")
    public String agencyList(ModelMap modelMap){
        List<Agency> agencies = agencyService.getAllAgency();
        List<House> houses = houseService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", houses);
        modelMap.put("agencyList", agencies);
        return "/user/agency/agencyList";
    }

    /**
     * 获取经纪人列表
     * @param pageSize
     * @param pageNum
     * @param modelMap
     * @return String
     */
    @RequestMapping("/agency/agentList")
    public String agentList(Integer pageSize, Integer pageNum, ModelMap modelMap){
        if (pageSize == null) {
            pageSize = 6;
        }
        PageData<User> ps = agencyService.getAllAgent(PageParams.build(pageSize, pageNum));
        List<House> houses =  houseService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", houses);
        modelMap.put("ps", ps);
        return "/user/agent/agentList";
    }

    /**
     * <h2>经纪人详情</h2>
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping("/agency/agentDetail")
    public String agentDetail(Long id,ModelMap modelMap){
        User user =  agencyService.getAgentDetail(id);
        List<House> houses =  houseService.getHotHouse(CommonConstants.RECOM_SIZE);
        House query = new House();
        query.setUserId(id);
        query.setBookmarked(false);
        PageData<House> bindHouse = houseService.queryHouse(query, new PageParams(3,1));
        if (bindHouse != null) {
            modelMap.put("bindHouses", bindHouse.getList()) ;
        }
        modelMap.put("recomHouses", houses);
        modelMap.put("agent", user);
        return "/user/agent/agentDetail";
    }

    @RequestMapping("/agency/agencyDetail")
    public String agencyDetail(Integer id, ModelMap modelMap){
        Agency agency =  agencyService.getAgency(id);
        List<House> houses =  houseService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", houses);
        modelMap.put("agency", agency);
        return "/user/agency/agencyDetail";
    }

    @RequestMapping("/agency/agentMsg")
    public String agentMsg(Long id, String msg, String name,String email, ModelMap modelMap){
        User user =  agencyService.getAgentDetail(id);
        mailService.sendSimpleMail("来自"+ email +"的咨询", msg, user.getEmail());
        return "redirect:/agency/agentDetail?id="+id +"&" + ResultMsg.successMsg("留言成功").asUrlParams();
    }
}
