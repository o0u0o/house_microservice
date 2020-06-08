package com.o0u0o.house.hsrv.common;

import lombok.Data;

/**
 * @Author aiuiot
 * @Date 2020/6/8 6:42 下午
 * @Descripton:
 **/
@Data
public class LimitOffset {

    private Integer limit;

    private Integer offset;

    public static LimitOffset build(Integer limit,Integer offset) {
        LimitOffset limitOffset = new LimitOffset();
        limitOffset.setLimit(limit);
        limitOffset.setOffset(offset);
        return limitOffset;
    }
}
