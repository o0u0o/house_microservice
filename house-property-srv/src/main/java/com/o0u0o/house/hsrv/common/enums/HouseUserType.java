package com.o0u0o.house.hsrv.common.enums;

/**
 * 房产用户类型
 * @author o0u0o
 * @date 2022/3/9 7:01 PM
 */
public enum HouseUserType {

    SALE(1),

    BOOKMARK(2);

    public  final Integer value;

    private HouseUserType(Integer value){
        this.value = value;
    }
}
