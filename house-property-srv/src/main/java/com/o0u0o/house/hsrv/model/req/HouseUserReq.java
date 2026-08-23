package com.o0u0o.house.hsrv.model.req;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>用户房产请求对象</h1>
 * @author o0u0o
 * @date 2022/3/10 10:58 AM
 */
@Getter
@Setter
public class HouseUserReq {

    private Long houseId;

    private Long userId;

    private Integer bindType;

    private boolean unBind;
}
