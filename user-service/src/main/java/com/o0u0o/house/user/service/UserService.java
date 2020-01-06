package com.o0u0o.house.user.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.o0u0o.house.user.mapper.UserMapper;
import com.o0u0o.house.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author aiuiot
 * @Date 2020/1/6 10:01 下午
 * @Descripton: 用户服务
 **/
@Service
public class UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    UserMapper userMapper;

    //文件服务器前缀
    @Value("${file.prefix}")
    private String imgPrefix;

    /**
     * 查询用户是高并发的接口，为了提高性能，减轻数据库压力
     * 借助redis 第一次通过数据库获取用户 设置到redis中
     * 根据用户ID查询
     *
     * step1：首先通过缓存获取用户对象
     * step2：如果缓存不存在 从数据库获取用户对象
     * step3：将用户对象写入Redis缓存 设置缓存时间5分钟
     * step4：返回对象
     * @param id
     */
    public User getUserById(Long id) {
        String key = "user:"+id;
        String json = redisTemplate.opsForValue().get(key);
        User user = null;
        //如果redis不存在
        if (Strings.isNullOrEmpty(json)){
            user = userMapper.selectById(id);
            user.setAvatar(imgPrefix + user.getAvatar());
            //设置到redis中
            String string = JSON.toJSONString(user);
            redisTemplate.opsForValue().set(key, string);
            redisTemplate.expire(key, 5, TimeUnit.MINUTES); //过期时间5分钟
        } else {
            user = JSON.parseObject(json, User.class);//将Redis 拿来的User json对象序列化为User对象
        }
        return user;
    }

    /**
     * 查询用户列表
     * 不是高并发接口，无需设置进redis
     * @return
     */
    public List<User> getUserByQuery(User user) {
        List<User> users = userMapper.select(user);
        users.forEach(user1 -> {
            user.setAvatar(imgPrefix + user.getAvatar());
        });
        return users;
    }
}
