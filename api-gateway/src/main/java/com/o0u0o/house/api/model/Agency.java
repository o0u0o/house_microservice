package com.o0u0o.house.api.model;

import lombok.Data;

/**
 * @Author aiuiot
 * @Date 2020/1/14 12:42 上午
 * @Descripton: 经纪人/机构
 **/
@Data
public class Agency {

    private Integer id;

    private String  name;

    private String  address;

    private String  phone;

    private String  email;

    private String  aboutUs;

    private String  webSite;

    private String  mobile;

}
