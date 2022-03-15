package com.o0u0o.house.comment.controller;

import com.o0u0o.house.comment.common.RestResponse;
import com.o0u0o.house.comment.model.Blog;
import com.o0u0o.house.comment.model.ListResponse;
import com.o0u0o.house.comment.model.req.BlogQueryReq;
import com.o0u0o.house.comment.service.BlogService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <h1>博客控制层</h1>
 * @author o0u0o
 * @date 2022/3/15 5:20 PM
 */
@RestController
@RequestMapping("blog")
public class BlogController {


    @Autowired
    private BlogService blogService;

    @RequestMapping("list")
    public RestResponse<ListResponse<Blog>> list(@RequestBody BlogQueryReq req){
        Pair<List<Blog>,Long> pair = blogService.queryBlog(req.getBlog(),req.getLimit(),req.getOffset());
        return RestResponse.success(ListResponse.build(pair.getKey(), pair.getValue()));
    }

    @RequestMapping("one")
    public RestResponse<Blog> one(Integer id){
        Blog blog = blogService.queryOneBlog(id);
        return RestResponse.success(blog);
    }
}
