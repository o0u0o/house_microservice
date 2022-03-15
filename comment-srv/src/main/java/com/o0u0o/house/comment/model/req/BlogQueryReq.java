package com.o0u0o.house.comment.model.req;

import com.o0u0o.house.comment.model.Blog;
import lombok.Getter;
import lombok.Setter;

/**
 * <h1>博客查询对象</h1>
 * @author o0u0o
 * @date 2022/3/15 5:23 PM
 */
@Getter
@Setter
public class BlogQueryReq {

    private Blog blog;

    private Integer limit;

    private Integer offset;
}
