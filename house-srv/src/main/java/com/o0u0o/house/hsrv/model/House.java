package com.o0u0o.house.hsrv.model;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <h1>房产实体类</h1>
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
    private Date    createTime;
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

    private String  sort = "time_desc";//price_desc,price_asc,time_desc

    private String priceStr;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getType() {
        return type;
    }

    public Integer getPrice() {
        return price;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public void setPrice(Integer price) {
        this.price = price;
        if (price != null) {
            this.priceStr = this.price + "万";
        }
    }

    public Integer getArea() {
        return area;
    }
    public void setArea(Integer area) {
        this.area = area;
    }
    public Integer getBaths() {
        return baths;
    }
    public void setBaths(Integer baths) {
        this.baths = baths;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getProperties() {
        return properties;
    }
    public void setProperties(String properties) {
        this.properties = properties;
        if (StringUtils.isNotBlank(properties)) {
            this.featureList = Splitter.on(",").splitToList(properties);
        }

    }
    public String getFloorPlan() {
        return floorPlan;
    }
    public void setFloorPlan(String floorPlan) {
        this.floorPlan = floorPlan;
        if (StringUtils.isNotBlank(floorPlan)) {
            this.floorPlanList = Splitter.on(",").splitToList(floorPlan);
        }

    }
    public boolean isBookmarked() {
        return bookmarked;
    }
    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }
    public String getTags() {
        return tags;
    }
    public List<Long> getIds() {
        return ids;
    }
    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public List<String> getImageList() {
        return imageList;
    }
    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public Integer getState() {
        return state;
    }

    public String getFirstImg() {
        return firstImg;
    }
    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
        if (StringUtils.isNotBlank(images)) {
            List<String> list =  Splitter.on(",").splitToList(images);
            if (list.size() > 0) {
                this.firstImg = list.get(0);
                this.imageList = list;
            }
        }
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public Integer getCommunityId() {
        return communityId;
    }
    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPriceStr() {
        return priceStr;
    }
    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    public Integer getBeds() {
        return beds;
    }
    public void setBeds(Integer beds) {
        this.beds = beds;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<String> getFloorPlanList() {
        return floorPlanList;
    }
    public void setFloorPlanList(List<String> floorPlanList) {
        this.floorPlanList = floorPlanList;
    }
    public List<String> getFeatureList() {
        return featureList;
    }
    public void setFeatureList(List<String> featureList) {
        this.featureList = featureList;
        this.properties = Joiner.on(",").join(featureList);
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
        if (rating != null) {
            this.roundRating = (int) Math.round(rating);
        }
    }
    public Integer getRoundRating() {
        return roundRating;
    }
    public void setRoundRating(Integer roundRating) {
        this.roundRating = roundRating;
    }

    @Override
    public String toString() {
        return "House [id=" + id + ", type=" + type + ", price=" + price + ", name=" + name
                + ", images=" + images + ", area=" + area + ", beds=" + beds + ", baths=" + baths
                + ", rating=" + rating + ", roundRating=" + roundRating + ", remarks=" + remarks
                + ", properties=" + properties + ", floorPlan=" + floorPlan + ", tags=" + tags + ", createTime=" + createTime + ", cityId=" + cityId
                + ", communityId=" + communityId + ", address=" + address + ", firstImg=" + firstImg
                + ", floorPlanList=" + floorPlanList + ", featureList=" + featureList
                + ", userId=" + userId  + ", bookmarked="
                + bookmarked + ", state=" + state + ", ids=" + ids + ", sort=" + sort + "]";
    }
}
