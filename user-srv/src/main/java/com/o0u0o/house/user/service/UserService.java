package com.o0u0o.house.user.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.o0u0o.house.user.common.UserException;
import com.o0u0o.house.user.mapper.UserMapper;
import com.o0u0o.house.user.model.User;
import com.o0u0o.house.user.utils.BeanHelper;
import com.o0u0o.house.user.utils.HashUtils;
import com.o0u0o.house.user.utils.JwtHelper;
import com.o0u0o.house.user.vo.UserVo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Map;
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
        //1、先查询邮箱是否被注册
        User isExistUser = userMapper.selectByEmail(user.getEmail());
        if (isExistUser != null){
            throw new UserException(UserException.Type.USER_IS_EXIST, "该邮箱已被用户注册！");
        }
        //加盐加密
        user.setPassword(HashUtils.encryPassword(user.getPassword()));
        //设置了用户的创建时间和更新时间
        BeanHelper.onInsert(user);
        userMapper.insert(user);
        //激活通知
        registerNotify(user.getEmail(), enableUrl);
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

    /**
     * <h2>校验用户名密码，生成token并返回</h2>
     * @param email
     * @param password
     * @return
     */
    public User auth(String email, String password) {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(password)){
            throw new UserException(UserException.Type.USER_AUTH_FAIL, "User Auth Fail.");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(HashUtils.encryPassword(password));
        user.setEnable(1);
        List<User> list = getUserByQuery(user);
        if (!list.isEmpty()){
            User retUser = list.get(0);
            onLogin(retUser);
            return retUser;
        }
        throw new UserException(UserException.Type.USER_AUTH_FAIL, "User Auth Fail.");
    }

    /**
     * 生成token
     */
    private void onLogin(User user) {
        String token = JwtHelper.genToken(ImmutableMap.of(
                "email", user.getEmail(),
                "name", user.getName(),
                "ts", Instant.now().getEpochSecond() + ""));
        renewToken(token, user.getEmail());
        user.setToken(token);
    }

    /**
     * <h2>重新生成Token</h2>
     * @return
     */
    private String renewToken(String token, String email){
        redisTemplate.opsForValue().set(email, token);
        //30分钟过期
        redisTemplate.expire(email, 30, TimeUnit.MINUTES);
        return token;
    }

    /**
     *
     * 通过token获取已登录的数据
     * @param token
     * @return
     */
    public User getLoginedUserByToken(String token) {
        Map<String, String> map = null;
        try {
            map = JwtHelper.verifyToken(token);
        } catch (Exception e){
            throw new UserException(UserException.Type.USER_NOT_LOGIN, "User not Login");
        }
        String email = map.get("email");
        Long expired = redisTemplate.getExpire(email);
        if (expired > 0){
            renewToken(token, email);
            User user =  getUserByEmail(email);
            user.setToken(token);
            return user;
        }
        throw new UserException(UserException.Type.USER_NOT_LOGIN, "User not Login");
    }

    /**
     * 通过Email 查询用户信息
     * @param email
     * @return
     */
    private User getUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        List<User> list = getUserByQuery(user);
        if (!list.isEmpty()){
            return list.get(0);
        }
        throw new UserException(UserException.Type.USER_NOT_LOGIN, "User Not Found" + email);
    }

    /**
     * 登出 Token 失效
     * @param token
     */
    public void invalidate(String token) {
        Map<String, String> map = JwtHelper.verifyToken(token);
        redisTemplate.delete(map.get("email"));
    }

    @Transactional(rollbackFor = Exception.class)
    public User updateUser(User user) {
        if (user.getEmail() == null) {
            return null;
        }
        if (!Strings.isNullOrEmpty(user.getPassword()) ) {
            user.setPassword(HashUtils.encryPassword(user.getPassword()));
        }
        userMapper.update(user);
        return userMapper.selectByEmail(user.getEmail());
    }

    /**
     * <h2>重置密码邮箱通知</h2>
     * @param email
     * @param url
     */
    public void resetNotify(String email,String url) {
        String randomKey = "reset_" + org.apache.commons.lang.RandomStringUtils.randomAlphabetic(10);
        redisTemplate.opsForValue().set(randomKey, email);
        redisTemplate.expire(randomKey, 1,TimeUnit.HOURS);
        String content = url +"?key="+  randomKey;
        mailService.sendSimpleMail("房产平台重置密码邮件", content, email);

    }

    /**
     * <h2>重置</h2>
     * @param key 秘钥
     * @param password 新密码
     * @return
     */
    public User reset(String key, String password) {
        String email = getResetKeyEmail(key);
        User updateUser = new User();
        updateUser.setEmail(email);
        updateUser.setPassword(HashUtils.encryPassword(password));
        userMapper.update(updateUser);
        return getUserByEmail(email);
    }

    public String getResetKeyEmail(String key) {
        return  redisTemplate.opsForValue().get(key);
    }
}
