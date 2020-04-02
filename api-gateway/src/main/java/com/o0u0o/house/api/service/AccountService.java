package com.o0u0o.house.api.service;

import com.google.common.collect.Lists;
import com.o0u0o.house.api.dao.UserDao;
import com.o0u0o.house.api.model.User;
import com.o0u0o.house.api.utils.BeanHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author aiuiot
 * @Date 2020/1/15 10:09 上午
 * @Descripton: 账号服务
 **/
@Service
public class AccountService {

    @Value("${domain.name}")
    private String domainName;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FileService fileService;

    /**
     * 激活邮箱
     * @param key 激活码
     * @return
     */
    public boolean enable(String key){
        return userDao.enable(key);
    }

    //判断当前邮箱是有被注册过
    public boolean isExist(String email) {
        return getUser(email) !=null;
    }

    //获取User
    private User getUser(String email) {
        User queryUser = new User();
        queryUser.setEmail(email);
        List<User> users = getUserByQuery(queryUser);
        if (!users.isEmpty()){
            return users.get(0);
        }
        return null;
    }

    //查询user
    private List<User> getUserByQuery(User queryUser) {
        return userDao.getUserList(queryUser);
    }

    /**
     * 添加用户
     * @param account
     */
    public boolean addAccount(User account) {
        //判断用户头像是否为空
        if (account.getAvatar() != null){
            List<String> imgs = fileService.getImgPaths(Lists.newArrayList(account.getAvatarFile()));
            account.setAvatar(imgs.get(0));
        }
        account.setEnableUrl("http://" + domainName+ "accounts/verify?key=");
        BeanHelper.setDefaultProp(account, User.class); //设置默认属性
        userDao.addUser(account);
        return true;
    }

    /**
     * 登录鉴权
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    public User auth(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return null;
        }
        User user = new User();
        user.setEmail(username);
        user.setPassword(password);
        try {
            user = userDao.authUser(user);
        } catch (Exception e){
            return null;
        }
        return user;
    }

    /**
     * 忘记密码
     * 调用重置通知接口
     * @param email 电子邮箱
     */
    @Async
    public void remember(String email){
        userDao.resetNotify(email,"http://" + domainName + "/accounts/reset");
    }
}
