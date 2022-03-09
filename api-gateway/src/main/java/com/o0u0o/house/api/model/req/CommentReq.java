package com.o0u0o.house.api.model.req;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>评论请求对象</h1>
 * @author o0u0o
 * @date 2022/3/9 4:09 PM
 */
@Getter
@Setter
public class CommentReq {

    private Long userId;

    private Long houseId;

    private Integer blogId;

    private String content;

    /** 1-房产，2-博客百科 */
    private Integer type;

    /** 获取多少评论 */
    private Integer size;
}
