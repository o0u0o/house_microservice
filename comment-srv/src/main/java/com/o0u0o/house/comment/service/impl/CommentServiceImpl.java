package com.o0u0o.house.comment.service.impl;

import com.o0u0o.house.comment.mapper.CommentMapper;
import com.o0u0o.house.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>评论业务实现类</h1>
 * @author o0u0o
 * @date 2022/3/15 9:54 PM
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


//    @Autowired
//    private UserDao userDao;


}
