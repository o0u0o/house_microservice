package com.o0u0o.house.api.model;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <h1>博客实体类</h1>
 * @author o0u0o
 * @date 2022/3/9 3:53 PM
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
