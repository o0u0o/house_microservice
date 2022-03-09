package com.o0u0o.house.hsrv.mapper;

import com.o0u0o.house.hsrv.common.LimitOffset;
import com.o0u0o.house.hsrv.model.Community;
import com.o0u0o.house.hsrv.model.House;
import com.o0u0o.house.hsrv.model.HouseUser;
import com.o0u0o.house.hsrv.model.UserMsg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <h1>房产映射对象</h1>
 * @Author aiuiot
 * @Date 2020/6/8 6:49 下午
 * @Descripton:
 **/
@Mapper
public interface HouseMapper {

    int insert(House house);

    /**
     * 查询房产列表
     * @param query
     * @param limitOffset
     * @return
     */
    List<House> selectHouse(@Param("house") House query,
                            @Param("pageParams") LimitOffset limitOffset);

    Long selectHouseCount(@Param("house") House query);

    List<Community> selectCommunity(Community community);

    int insertUserMsg(UserMsg userMsg);

    int updateHouse(House house);

    HouseUser selectHouseUser(@Param("userId") long userID,
                              @Param("id") long id,
                              @Param("type") int type);

    HouseUser selectHouseUserById(@Param("id") long id,
                                  @Param("type") int type);

    int insertHouseUser(HouseUser houseUser);

    int delete(Long id);

    int downHouse(Long id);

    /**
     * 删除房产用户
     * @param id
     * @param userId
     * @param type
     * @return
     */
    int deleteHouseUser(@Param("id") Long id,
                        @Param("userId") Long userId,
                        @Param("type") Integer type);
}
