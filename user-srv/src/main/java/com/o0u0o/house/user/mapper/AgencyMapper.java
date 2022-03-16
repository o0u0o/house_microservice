package com.o0u0o.house.user.mapper;

import com.o0u0o.house.user.common.PageParams;
import com.o0u0o.house.user.model.Agency;
import com.o0u0o.house.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/1/14 12:38 上午
 * @Descripton: 经纪人/机构
 **/
@Mapper
public interface AgencyMapper {

    List<Agency> select(Agency agency);

    int insert(Agency agency);

    List<User> selectAgent(@Param("user") User user,
                           @Param("pageParams") PageParams pageParams);

    Long selectAgentCount(@Param("user") User user);
}
