package com.o0u0o.house.hsrv.model;

import lombok.Data;

/**
 * @Author aiuiot
 * @Date 2020/6/8 6:31 下午
 * @Descripton:
 **/
@Data
public class HouseQueryReq {

    private House query;

    private Integer limit;

    private Integer offset;
}
