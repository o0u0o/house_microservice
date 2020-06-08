package com.o0u0o.house.api.common;


import com.o0u0o.house.api.model.User;

/**
 * @Author aiuiot
 * @Date 2020/1/15 2:03 下午
 * @Descripton: 用户上下文
 **/
public class UserContext {
    public static final ThreadLocal<User> USER_HOLDER = new ThreadLocal<>();

    public static void setUser(User user) {
        USER_HOLDER.set(user);
    }

    public static void remove() {
        USER_HOLDER.remove();
    }

    public static User getUser(){
        return USER_HOLDER.get();
    }
}
