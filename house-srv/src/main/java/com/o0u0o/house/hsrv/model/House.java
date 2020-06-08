package com.o0u0o.house.hsrv.model;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/6/8 6:27 下午
 * @Descripton: 房产实体类
 **/
@Data
public class House implements Serializable {

    private static final long serialVersionUID = 7819196881708604665L;

    private Long id;
    private Integer type;
    private Integer price;
    private String  name;
    private String images;
    private Integer area;
    private Integer beds;
    private Integer baths;
    private Double  rating;

    private Integer roundRating = 0;
    private String  remarks;
    private String  properties;
    private String  floorPlan;
    private String  tags;
    private Date createTime;
    private Integer cityId;
    private Integer    communityId;

    private String     address;

    private String     firstImg;

    private List<String> imageList = Lists.newArrayList();

    private List<String> floorPlanList = Lists.newArrayList();

    private List<String> featureList   = Lists.newArrayList();

    private Long userId;

    private boolean bookmarked;

    private Integer state;

    private List<Long> ids;

    //price_desc,price_asc,time_desc
    private String  sort = "time_desc";

    private String priceStr;
}
