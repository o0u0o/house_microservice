package com.o0u0o.house.api.model;

import lombok.Data;

/**
 * @Author aiuiot
 * @Date 2020/6/8 5:47 下午
 * @Descripton:
 **/
@Data
public class HouseQueryReq {

    private House query;

    private Integer limit;

    private Integer offset;
}
