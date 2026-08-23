package com.o0u0o.house.comment.controller;

import com.google.common.base.Objects;
import com.o0u0o.house.comment.common.RestResponse;
import com.o0u0o.house.comment.model.Comment;
import com.o0u0o.house.comment.model.req.CommentReq;
import com.o0u0o.house.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <h1>评论列表</h1>
 * @author YueChuanGui
 * @date 2022/3/15 9:52 PM
 */

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     * @param commentReq
     * @return
     */
    @RequestMapping(value="add")
    public RestResponse<Object> leaveComment(@RequestBody CommentReq commentReq){
        Integer type = commentReq.getType();
        if (Objects.equal(1, type)) {
            //添加房产评论
            commentService.addHouseComment(commentReq.getHouseId(),commentReq.getContent(),commentReq.getUserId());
        }else {
            //添加百科评论
            commentService.addBlogComment(commentReq.getBlogId(),commentReq.getContent(),commentReq.getUserId());
        }
        return RestResponse.success();
    }

    /**
     * 评论列表
     * @param commentReq
     * @return
     */
    @RequestMapping("list")
    public RestResponse<List<Comment>> list(@RequestBody CommentReq commentReq){
        Integer type = commentReq.getType();
        List<Comment> list = null;
        if (Objects.equal(1, type)) {
            //查询房产评论列表
            list = commentService.getHouseComments(commentReq.getHouseId(), commentReq.getSize());
        }else {
            //查询百科评论列表
            list = commentService.getBlogComments(commentReq.getBlogId(), commentReq.getSize());
        }
        return RestResponse.success(list);
    }
}
