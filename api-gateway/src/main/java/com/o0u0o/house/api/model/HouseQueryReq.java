package com.o0u0o.house.api.model;

import lombok.Data;

/**
 * @Author aiuiot
 * @Date 2020/4/2 10:51 下午
 * @Descripton: 房产查询请求
 **/
@Data
public class HouseQueryReq {

    private House query;

    private Integer limit;

    private Integer offset;
}
