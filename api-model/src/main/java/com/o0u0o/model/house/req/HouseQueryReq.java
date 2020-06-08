package com.o0u0o.model.house.req;

import com.o0u0o.model.house.House;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author aiuiot
 * @Date 2020/4/2 10:51 下午
 * @Descripton: 房产查询请求
 **/
@Data
public class HouseQueryReq implements Serializable {

    private static final long serialVersionUID = 8917750604260238661L;

    private House query;

    private Integer limit;

    private Integer offset;
}
