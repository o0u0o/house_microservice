package com.o0u0o.house.user.common;

import com.o0u0o.house.user.exception.WithTypeException;

/**
 * @Author aiuiot
 * @Date 2020/1/13 11:53 上午
 * @Descripton: 用户异常
 **/
public class UserException extends RuntimeException implements WithTypeException {

    private Type type;

    public UserException(Type type, String message){
        super(message);
        this.type = type;
    }

    public Type type(){
        return type;
    }

    public enum Type{
        USER_NOT_LOGIN,
        USER_NOT_FOUND,
        USER_AUTH_FAIL;
    }

}
