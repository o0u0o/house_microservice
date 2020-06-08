package com.o0u0o.house.api.model;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/6/8 5:32 下午
 * @Descripton: 房产实体类
 **/
@Data
public class House implements Serializable {

    private static final long serialVersionUID = 7826508067568483847L;

    /** 主键ID */
    private Long id;

    /** 类型 */
    private Integer type;

    /** 价格 */
    private Integer price;

    /** 名字 */
    private String name;

    /** 图片 */
    private String images;

    /** 面积 */
    private Integer area;

    /** 卧室数量 */
    private Integer beds;

    /** 卫生间数量 */
    private Integer baths;

    /** 评分 */
    private Double rating;

    /** 描述 */
    private String remarks;

    /** 属性信息 */
    private String properties;

    /** 户型图 */
    private String floorPlan;

    /** 标签 */
    private String tags;

    /** 城市ID */
    private Integer cityId;

    /** 社区（小区）ID */
    private Integer communityId;

    /** 地址 */
    private String address;

    /** 首图 */
    private String firstImg;

    private Long userId; 		// 拥有盖房屋的用户ID
    private boolean bookmarked;	// 是否收藏
    private Integer state;		// 状态
    private List<Long> ids;

    private List<String> imageList = Lists.newArrayList();

    private List<String> floorPlanList = Lists.newArrayList();

    private  List<String> featureList = Lists.newArrayList();

    private List<MultipartFile> houseFiles;

    private List<MultipartFile> floorPlanFiles;



    /** 排序 默认按照时间顺序进行排序 */
    private String sort = "time_desc";

    /** 创建时间 */
    private Date createTime;
}
