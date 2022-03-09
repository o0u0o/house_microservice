package com.o0u0o.house.hsrv.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author aiuiot
 * @Date 2020/6/8 6:59 下午
 * @Descripton:
 **/
@Data
public class HouseUser {

    private Long id;

    private Long houseId;

    private Long userId;

    private Date createTime;

    private Integer type;
}
