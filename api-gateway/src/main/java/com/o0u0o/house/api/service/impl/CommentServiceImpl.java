package com.o0u0o.house.api.service.impl;

import com.o0u0o.house.api.common.PageData;
import com.o0u0o.house.api.common.PageParams;
import com.o0u0o.house.api.dao.CommentDao;
import com.o0u0o.house.api.model.Blog;
import com.o0u0o.house.api.model.Comment;
import com.o0u0o.house.api.model.req.CommentReq;
import com.o0u0o.house.api.service.CommentService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>评论业务实现类</h1>
 * @author o0u0o
 * @date 2022/3/9 3:50 PM
 */
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentDao commentDao;


    /**
     * <h2>查询博客论</h2>
     *
     * @param query
     * @param build
     * @return
     */
    @Override
    public PageData<Blog> queryBlogs(Blog query, PageParams build) {
        Pair<List<Blog>, Long> pair = commentDao.getBlogs(query,build.getLimit(),build.getOffset());
        return PageData.buildPage(pair.getKey(), pair.getValue(), build.getPageSize(), build.getPageNum());
    }

    @Override
    public Blog queryOneBlog(int id) {
        return null;
    }

    /**
     * <h2>查询房产评论</h2>
     * @param id
     * @return
     */
    @Override
    public List<Comment> getHouseComments(long id) {
        CommentReq commentReq = new CommentReq();
        commentReq.setHouseId(id);
        commentReq.setType(1);
        commentReq.setSize(8);
        return commentDao.listComment(commentReq);
    }
}
