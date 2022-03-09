package com.o0u0o.house.api.service;

import com.o0u0o.house.api.common.PageData;
import com.o0u0o.house.api.common.PageParams;
import com.o0u0o.house.api.model.Blog;
import com.o0u0o.house.api.model.Comment;

import java.util.List;

/**
 * <h1>评论业务接口</h1>
 * @author o0u0o
 * @date 2022/3/9 3:50 PM
 */
public interface CommentService {

    /**
     * <h2>查询博客论</h2>
     * @param query
     * @param build
     * @return
     */
    PageData<Blog> queryBlogs(Blog query, PageParams build);

    Blog queryOneBlog(int id);

    /**
     * <h2>查询房产评论</h2>
     * @param id
     * @return
     */
    List<Comment> getHouseComments(long id);
}
