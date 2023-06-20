package com.o0u0o.house.api.controller;

import com.google.common.base.Objects;
import com.o0u0o.house.api.common.*;
import com.o0u0o.house.api.model.Comment;
import com.o0u0o.house.api.model.UserMsg;
import com.o0u0o.house.api.service.AgencyService;
import com.o0u0o.house.api.service.CommentService;
import com.o0u0o.house.api.service.HouseService;
import com.o0u0o.house.api.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * <h1>房产控制层</h1>
 * @Author o0u0o
 * @Date 2020/4/2 10:39 下午
 * @Descripton: 房产接口
 **/
@RequestMapping("house")
@Controller
public class HouseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private CommentService commentService;

    /**
     * 房产列表
     * @param pageSize 第几页
     * @param pageNum  每页大小
     * @param query
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "list", method = {RequestMethod.POST, RequestMethod.GET})
    public String houseList(Integer pageSize, Integer pageNum, House query, ModelMap modelMap){
        PageData<House> ps = houseService.queryHouse(query, PageParams.build(pageSize, pageNum));
        List<House> rcHouses =  houseService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", rcHouses);
        modelMap.put("vo", query);
        modelMap.put("ps", ps);
        return "/house/listing";
    }

    @RequestMapping(value="detail", method={RequestMethod.POST, RequestMethod.GET})
    public String houseDetail(long id, ModelMap modelMap){
        House house = houseService.queryOneHouse(id);
        List<Comment> comments = commentService.getHouseComments(id);
        List<House> rcHouses =  houseService.getHotHouse(CommonConstants.RECOM_SIZE);
        if (house.getUserId() != null) {
            if (!Objects.equal(0L, house.getUserId())) {
                modelMap.put("agent", agencyService.getAgentDetail(house.getUserId()));
            }
        }
        modelMap.put("house", house);
        modelMap.put("recomHouses", rcHouses);
        modelMap.put("commentList", comments);
        return "/house/detail";
    }

    @RequestMapping(value="leaveMsg",method={RequestMethod.POST,RequestMethod.GET})
    public String houseMsg(UserMsg userMsg){
        houseService.addUserMsg(userMsg);
        return "redirect:/house/detail?id=" + userMsg.getHouseId() + "&" + ResultMsg.successMsg("留言成功").asUrlParams();
    }

    @ResponseBody
    @RequestMapping(value="rating",method={RequestMethod.POST,RequestMethod.GET})
    public ResultMsg houseRate(Double rating, Long id){
        houseService.updateRating(id, rating);
        return ResultMsg.success();
    }

}
