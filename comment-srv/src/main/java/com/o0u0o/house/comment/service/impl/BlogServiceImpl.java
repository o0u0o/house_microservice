package com.o0u0o.house.comment.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.o0u0o.house.comment.mapper.BlogMapper;
import com.o0u0o.house.comment.model.Blog;
import com.o0u0o.house.comment.model.LimitOffset;
import com.o0u0o.house.comment.service.BlogService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>博客业务实现类</h1>
 * @author o0u0o
 * @date 2022/3/15 5:22 PM
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    /**
     * <h2>查询百科</h2>
     *
     * @param blog
     * @param limit
     * @param offset
     * @return
     */
    @Override
    public Pair<List<Blog>, Long> queryBlog(Blog blog, Integer limit, Integer offset) {
        List<Blog> blogs = blogMapper.selectBlog(blog, LimitOffset.build(limit, offset));
        populate(blogs);
        Long count =  blogMapper.selectBlogCount(blog);
        return ImmutablePair.of(blogs, count);
    }

    /**
     * <h2>查询一条百科</h2>
     * @param id 百科id
     * @return
     */
    @Override
    public Blog queryOneBlog(Integer id) {
        Blog query = new Blog();
        query.setId(id);
        List<Blog> blogs = blogMapper.selectBlog(query, LimitOffset.build(1, 0));
        if (!blogs.isEmpty()) {
            return blogs.get(0);
        }
        return null;
    }

    /**
     *
     * @param blogs
     */
    private void populate(List<Blog> blogs) {
        if (!blogs.isEmpty()) {
            blogs.stream().forEach(item -> {
                String stripped =  Jsoup.parse(item.getContent()).text();
                item.setDigest(stripped.substring(0, Math.min(stripped.length(),40)));
                String tags = item.getTags();
                item.getTagList().addAll(Lists.newArrayList(Splitter.on(",").split(tags)));
            });
        }
    }
}
