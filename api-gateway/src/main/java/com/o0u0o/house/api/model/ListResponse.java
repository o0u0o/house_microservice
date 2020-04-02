package com.o0u0o.house.api.model;

import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/4/2 10:48 下午
 * @Descripton: LIST相应数据
 **/
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
