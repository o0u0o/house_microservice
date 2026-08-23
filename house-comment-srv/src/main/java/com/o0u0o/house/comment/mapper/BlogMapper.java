package com.o0u0o.house.comment.mapper;

import com.o0u0o.house.comment.model.Blog;
import com.o0u0o.house.comment.model.LimitOffset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <h1>博客映射对象</h1>
 * @author o0u0o
 * @date 2022/3/9 10:30 PM
 */
@Mapper
public interface BlogMapper {

    public List<Blog> selectBlog(@Param("blog") Blog blog,
                                 @Param("pageParams") LimitOffset limitOffset);

    public Long selectBlogCount(Blog query);

}
