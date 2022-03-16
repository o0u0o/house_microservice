package com.o0u0o.house.api.controller;

import com.o0u0o.house.api.common.CommonConstants;
import com.o0u0o.house.api.common.PageData;
import com.o0u0o.house.api.common.PageParams;
import com.o0u0o.house.api.model.Blog;
import com.o0u0o.house.api.model.Comment;
import com.o0u0o.house.api.model.House;
import com.o0u0o.house.api.service.CommentService;
import com.o0u0o.house.api.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <h1>百科(博客)控制层</h1>
 * @author o0u0o
 * @date 2022/3/9 3:48 PM
 */
@Controller
public class BlogController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private HouseService houseService;

    /**
     * <h2>百科列表</h2>
     * @param pageSize
     * @param pageNum
     * @param query
     * @param modelMap
     * @return
     */
    @RequestMapping(value="blog/list",method={RequestMethod.POST, RequestMethod.GET})
    public String list(Integer pageSize, Integer pageNum, Blog query, ModelMap modelMap){
        PageData<Blog> ps = commentService.queryBlogs(query, PageParams.build(pageSize, pageNum));
        List<House> houses =  houseService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", houses);
        modelMap.put("ps", ps);
        return "/blog/listing";
    }

    /**
     * <h2>百科详情</h2>
     * @param id 博客ID
     * @param modelMap
     * @return
     */
    @RequestMapping(value="blog/detail",method={RequestMethod.POST,RequestMethod.GET})
    public String blogDetail(int id, ModelMap modelMap){
        Blog blog = commentService.queryOneBlog(id);
        List<Comment> comments = commentService.getBlogComments(id);
        List<House> houses = houseService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", houses);
        modelMap.put("blog", blog);
        modelMap.put("commentList", comments);
        return "/blog/detail";
    }
}
