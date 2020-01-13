package com.o0u0o.house.user.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.o0u0o.house.user.common.UserException;
import com.o0u0o.house.user.mapper.UserMapper;
import com.o0u0o.house.user.model.User;
import com.o0u0o.house.user.utils.BeanHelper;
import com.o0u0o.house.user.utils.HashUtils;
import com.o0u0o.house.user.vo.UserVo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    MailService mailService;

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

    /**
     * 注册：添加用户
     * @param user 用户信息
     * @param enableUrl 激活连接
     */
    public void addAccount(User user, String enableUrl) {
        user.setPassword(HashUtils.encryPassword(user.getPassword()));  //加盐加密
        BeanHelper.onInsert(user);  //设置了用户的创建时间和更新时间
        userMapper.insert(user);
        registerNotify(user.getEmail(), enableUrl); //激活通知

    }

    /**
     * 注册通知
     * @param email 邮箱
     * @param enableUrl 激活连接
     */
    private void registerNotify(String email, String enableUrl) {
        //生成一个随机key
        String randomKey = HashUtils.hashString(email) + RandomStringUtils.randomAlphabetic(10);
        redisTemplate.opsForValue().set(randomKey, email);
        redisTemplate.expire(randomKey, 1, TimeUnit.HOURS); //过期时间为1小时
        String content = enableUrl + "?key="+randomKey;
        mailService.sendSimpleMail("房产平台激活邮箱", content, email);
    }


    /**
     * 激活邮箱
     * @param key
     */
    public boolean enable(String key) {
        String email = redisTemplate.opsForValue().get(key);
        if (StringUtils.isBlank(email)){
            throw new UserException(UserException.Type.USER_NOT_FOUND, "无效的key");
        }
        User updateUser = new User();
        updateUser.setEmail(email);
        updateUser.setEnable(1);
        userMapper.update(updateUser);
        return true;
    }
}
