package com.o0u0o.house.api.controller;

import com.o0u0o.house.api.common.UserContext;
import com.o0u0o.house.api.model.User;
import com.o0u0o.house.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <h1>[API-GATEWAY]评论控制器</h1>
 * ClassName: CommentController
 * Description:
 *
 * @Author o0u0o
 * @Date 2023/6/20 3:09 PM
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    @RequestMapping(value="comment/leaveComment")
    public String leaveComment(String content, Long houseId, ModelMap modelMap){
        User user = UserContext.getUser();
        Long userId =  user.getId();
        commentService.addHouseComment(houseId,content,userId);
        return "redirect:/house/detail?id=" + houseId;
    }

    @RequestMapping(value="comment/leaveBlogComment")
    public String leaveBlogComment(String content,Integer blogId,ModelMap modelMap){
        User user = UserContext.getUser();
        Long userId =  user.getId();
        commentService.addBlogComment(blogId,content,userId);
        return "redirect:/blog/detail?id=" + blogId;
    }
}
