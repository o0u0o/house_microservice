package com.o0u0o.house.hsrv.model;

import lombok.Data;

import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/6/8 6:32 下午
 * @Descripton:
 **/
@Data
public class ListResponse<T> {

    private List<T> list;

    private Long count;

    public static <T> ListResponse<T> build(List<T> list,Long count) {
        ListResponse<T> response = new ListResponse<>();
        response.setCount(count);
        response.setList(list);
        return response;
    }
}
