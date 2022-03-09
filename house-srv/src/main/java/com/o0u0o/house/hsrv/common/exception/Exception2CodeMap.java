package com.o0u0o.house.hsrv.common.exception;

import com.google.common.collect.ImmutableMap;
import com.o0u0o.house.hsrv.common.RestCode;

/**
 * <h1>异常代码</h1>
 * @author o0u0o
 * @date 2022/3/9 7:29 PM
 */
public class Exception2CodeMap {

    private static final ImmutableMap<Object, RestCode> MAP = ImmutableMap.<Object, RestCode>builder()
            .put(IllegalParamsException.Type.WRONG_PAGE_NUM, RestCode.WRONG_PAGE)
            .build();

    public static ImmutableMap<Object, RestCode>  getMap(){
        return MAP;
    }


    public static RestCode getCode(Object exOrType){
        RestCode code = MAP.get(exOrType);
        if (code == null) {
            return RestCode.UNKNOWN_ERROR;
        }
        return code;
    }
}
