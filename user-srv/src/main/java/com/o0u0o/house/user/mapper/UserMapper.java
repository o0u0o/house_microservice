package com.o0u0o.house.user.mapper;

import com.o0u0o.house.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/1/6 10:07 下午
 * @Descripton:
 **/
@Mapper
public interface UserMapper {

    //根据用户ID查询
    User selectById(Long id);

    //查询用户列表
    List<User> select(User user);

    //更新用户
    public int update(User user);

    //插入用户
    public int insert(User account);

    //删除用户
    public int delete(String email);

}
