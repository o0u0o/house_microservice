package com.o0u0o.house.comment.model;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <h1>博客</h1>
 * @author o0u0o
 * @date 2022/3/9 10:38 PM
 */
@Data
public class Blog {

    private Integer id;

    private String  tags;

    private String  content;

    private String  title;

    private Date createTime;

    private String  digest;

    private List<String> tagList = Lists.newArrayList();
}
