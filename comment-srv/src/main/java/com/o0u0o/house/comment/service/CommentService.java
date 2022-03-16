package com.o0u0o.house.comment.service;

import com.o0u0o.house.comment.model.Comment;

import java.util.List;

/**
 * <h1>评论业务接口</h1>
 * @author o0u0o
 * @date 2022/3/15 9:53 PM
 */
public interface CommentService {

    /**
     * <h2>添加房产评论</h2>
     * @param houseId
     * @param content
     * @param userId
     */
    void addHouseComment(Long houseId, String content, Long userId);

    /**
     * <h2>添加百科评论</h2>
     * @param blogId
     * @param content
     * @param userId
     */
    void addBlogComment(Integer blogId, String content, Long userId);

    /**
     * <h2>查询房产列表</h2>
     * @param houseId
     * @param size
     * @return
     */
    List<Comment> getHouseComments(Long houseId, Integer size);

    /**
     * <h2>查询百科列表</h2>
     * @param blogId
     * @param size
     * @return
     */
    List<Comment> getBlogComments(Integer blogId, Integer size);
}
