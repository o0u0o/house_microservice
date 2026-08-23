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

    /**
     * <h2>新增用户</h2>
     * @param account
     * @return
     */
    public int insert(User account);

    /**
     * <h2>删除用户</h2>
     * @param email
     * @return
     */
    public int delete(String email);

    /**
     * <h2>通过邮箱查询用户</h2>
     * @param email 邮箱
     * @return User用户对象
     */
    User selectByEmail(String email);

}
