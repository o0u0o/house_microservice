package com.o0u0o.house.comment.model;

import java.util.List;

/**
 * <h1>列表响应对象</h1>
 * @author o0u0o
 * @date 2022/3/15 5:24 PM
 */
public class ListResponse<T> {

    private List<T> list;

    private Long count;

    public static <T> ListResponse<T> build(List<T> list,Long count) {
        ListResponse<T> response = new ListResponse<>();
        response.setCount(count);
        response.setList(list);
        return response;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ListResponse [list=" + list + ", count=" + count + "]";
    }
}
