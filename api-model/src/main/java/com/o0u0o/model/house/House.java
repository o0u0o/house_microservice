package com.o0u0o.model.house;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/1/5 11:22 上午
 * @Descripton: 房产模型
 **/
@Data
public class House implements Serializable {

    private static final long serialVersionUID = -3473229769401244581L;

    private Long id;

    private Integer type;

    private Integer price;

    private String name;

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

    private List<MultipartFile> houseFiles;

    private List<MultipartFile> floorPlanFiles;


    private String priceStr;

    private String typeStr;


    private Long userId;

    private boolean bookmarked;

    private Integer state;

    private List<Long> ids;

    //price_desc,price_asc,time_desc
    private String  sort = "time_desc";

}
