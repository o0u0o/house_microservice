package com.o0u0o.house.user.model;

import lombok.Data;

import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/1/14 12:13 上午
 * @Descripton: 分页
 **/
@Data
public class ListResponse<T> {
    private List<T> list;

    private Long count;

    public static <T> ListResponse<T> build(List<T> list, Long count){
        ListResponse response = new ListResponse<>();
        response.setCount(count);
        response.setList(list);
        return response;
    }

    public ListResponse() {

    }

    public ListResponse(List<T> list, Long count) {
        this.list = list;
        this.count = count;
    }
}
