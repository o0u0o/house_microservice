package com.o0u0o.house.hsrv.service.impl;

import com.google.common.collect.Lists;
import com.o0u0o.house.hsrv.common.BeanHelper;
import com.o0u0o.house.hsrv.common.LimitOffset;
import com.o0u0o.house.hsrv.common.enums.HouseUserType;
import com.o0u0o.house.hsrv.dao.UserDao;
import com.o0u0o.house.hsrv.mapper.HouseMapper;
import com.o0u0o.house.hsrv.model.*;
import com.o0u0o.house.hsrv.service.HouseService;
import com.o0u0o.house.hsrv.service.MailService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author aiuiot
 * @Date 2020/6/8 6:44 下午
 * @Descripton: 房产服务实现
 **/

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserDao userDao;


    @Value("${file.prefix}")
    private String imgPrefix;

    /**
     * 查询和设置图片
     * @param query
     * @param pageParams
     * @return
     */
    @Override
    public List<House> queryAndSetImg(House query, LimitOffset pageParams){
        List<House> houses =  houseMapper.selectHouse(query, pageParams);
        houses.forEach(h -> {
            h.setFirstImg(imgPrefix + h.getFirstImg());
            h.setImageList(h.getImageList().stream().map(img -> imgPrefix + img).collect(Collectors.toList()));
            h.setFloorPlanList(h.getFloorPlanList().stream().map(img -> imgPrefix + img).collect(Collectors.toList()));
        });
        return houses;
    }

    /**
     * <h2>向经纪人发送留言通知</h2>
     * @param userMsg
     */
    @Override
    public void addUserMsg(UserMsg userMsg) {
        //1、设置默认值
        BeanHelper.onInsert(userMsg);
        BeanHelper.setDefaultProp(userMsg, UserMsg.class);
        houseMapper.insertUserMsg(userMsg);
        //2、获取经纪人的邮箱地址
        User user = userDao.getAgentDetail(userMsg.getAgentId());
        mailService.sendSimpleMail("来自用户:" + userMsg.getEmail(), userMsg.getMsg(), user.getEmail());
    }

    /**
     * <h2>更新评分</h2>
     * @param id
     * @param rating
     */
    @Override
    public void updateRating(Long id, Double rating) {
        House house = queryOneHouse(id);
        Double oldRating = house.getRating();
        //新评分
        Double newRating = oldRating.equals(0D) ? rating : Math.min(Math.round(oldRating + rating)/2, 5);
        House updateHouse =  new House();
        updateHouse.setId(id);
        updateHouse.setRating(newRating);
        houseMapper.updateHouse(updateHouse);
    }

    /**
     * <h1>查询房产</h1>
     * @param query
     * @param build
     * @return
     */
    @Override
    public Pair<List<House>, Long> queryHouse(House query, LimitOffset build) {
        List<House> houses = Lists.newArrayList();
        House houseQuery = query;
        //如果房产名为空就搜索小区
        if (StringUtils.isNoneBlank(query.getName())) {
            Community community = new Community();
            community.setName(query.getName());
            // 查询小区
            List<Community> communities = houseMapper.selectCommunity(community);
            if (!communities.isEmpty()) {
                houseQuery = new House();
                houseQuery.setCommunityId(communities.get(0).getId());
            }
        }
        // 处理房产图片
        houses = queryAndSetImg(houseQuery, build);
        Long count = houseMapper.selectHouseCount(houseQuery);
        return ImmutablePair.of(houses, count);
    }

    /**
     * <h2>查询单个房产</h2>
     * @param id 房产ID
     * @return
     */
    @Override
    public House queryOneHouse(long id) {
        House query = new House();
        query.setId(id);
        //只查询一个房产
        List<House> houses = queryAndSetImg(query, LimitOffset.build(1, 0));
        if (!houses.isEmpty()) {
            return houses.get(0);
        }
        return null;
    }

    /**
     * <h2>新增房产</h2>
     * 1. 添加房产
     * 2. 绑定房产到用户关系
     * @param house
     * @param userId
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public void addHouse(House house, Long userId) {
        BeanHelper.setDefaultProp(house, House.class);
        BeanHelper.onInsert(house);
        houseMapper.insert(house);
        bindUser2House(house.getId(),userId, HouseUserType.SALE);
    }

    /**
     * <h2>用户绑定房产</>
     * @param id
     * @param userId
     * @param sale
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public void bindUser2House(Long id, Long userId, HouseUserType sale) {
        HouseUser existHouseUser = houseMapper.selectHouseUser(userId, id, sale.value);
        if (existHouseUser != null) {
            return;
        }
        HouseUser houseUser  = new HouseUser();
        houseUser.setHouseId(id);
        houseUser.setUserId(userId);
        houseUser.setType(sale.value);
        BeanHelper.setDefaultProp(houseUser, HouseUser.class);
        BeanHelper.onInsert(houseUser);
        houseMapper.insertHouseUser(houseUser);
    }

    /**
     * <h1>用户解绑房产</h1>
     * 注意这里逻辑做了修改:当售卖时只能将房产下架，不能解绑用户关系
     * 当收藏时可以解除用户收藏房产这一关系
     * @param houseId 房产I  类型D
     * @param userId 用户ID
     * @param type
     */
    @Override
    public void unbindUser2Houser(Long houseId, Long userId, HouseUserType type) {
        if (type.equals(HouseUserType.SALE)) {
            houseMapper.downHouse(houseId);
        }
        else {
            houseMapper.deleteHouseUser(houseId, userId, type.value);
        }
    }
}
