package com.o0u0o.house.user.mapper;

import com.o0u0o.house.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <h1>用户实体映射对象</h1>
 * @Author aiuiot
 * @Date 2020/1/6 10:07 下午
 * @Descripton:
 **/
@Mapper
public interface UserMapper {

    /**
     * <h2>根据用户ID查询</h2>
     * @param id
     * @return
     */
    User selectById(Long id);


    /**
     * <h2>查询用户列表</h2>
     * @param user
     * @return
     */
    List<User> select(User user);

    /**
     * <h2>更新用户</h2>
     * @param user
     * @return
     */
    public int update(User user);

    //插入用户
    public int insert(User account);

    //删除用户
    public int delete(String email);

}
