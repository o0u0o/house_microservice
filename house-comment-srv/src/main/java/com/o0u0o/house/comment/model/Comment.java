package com.o0u0o.house.comment.model;

import lombok.Data;

import java.util.Date;

/**
 * <h1>评论实体类</h1>
 * @author o0u0o
 * @date 2022/3/9 11:08 PM
 */
@Data
public class Comment {
    private Long id;
    private String content;
    private Long houseId;
    private Date createTime;
    private Integer blogId;
    private Integer type;
    private Long userId;

    private String  userName;
    private String  avatar;
}
