package com.o0u0o.house.comment.service;

import com.o0u0o.house.comment.model.Blog;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * <h1>博客业务接口类</h1>
 * @author o0u0o
 * @date 2022/3/15 5:21 PM
 */
public interface BlogService {

    /**
     * <h2>查询百科</h2>
     * @param blog
     * @param limit
     * @param offset
     * @return
     */
    Pair<List<Blog>, Long> queryBlog(Blog blog, Integer limit, Integer offset);

    /**
     * <h2>查询一条百科</h2>
     * @param id
     * @return
     */
    Blog queryOneBlog(Integer id);
}
