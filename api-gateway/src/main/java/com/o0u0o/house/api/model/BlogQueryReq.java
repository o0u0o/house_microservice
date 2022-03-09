package com.o0u0o.house.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>博客查询请求对象</h1>
 * @author o0u0o
 * @date 2022/3/9 3:56 PM
 */
@Getter
@Setter
public class BlogQueryReq {

    private Blog blog;

    private Integer limit;

    private Integer offset;
}
