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
     * <h2>添加房产评论</h2>
     * @param houseId 房产ID
     * @param content 评论内容
     * @param userId 用户ID
     */
    void addHouseComment(Long houseId, String content, Long userId);

    /**
     * <h2>添加博客评论</h2>
     * @param blogId 博客ID
     * @param content 评论内容
     * @param userId 用户ID
     */
    void addBlogComment(Integer blogId, String content, Long userId);

    /**
     * <h2>查询博客论</h2>
     * @param query
     * @param build
     * @return PageData<Blog>
     */
    PageData<Blog> queryBlogs(Blog query, PageParams build);

    Blog queryOneBlog(int id);

    /**
     * <h2>查询房产评论</h2>
     * @param id
     * @return List<Comment>
     */
    List<Comment> getHouseComments(long id);

    /**
     * <h2>获取博客评论</h2>
     * @param id 博客ID
     * @return List<Comment>
     */
    List<Comment> getBlogComments(int id);
}
