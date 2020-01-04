package com.o0u0o.house.user.exception;

/**
 * @Author aiuiot
 * @Date 2020/1/4 10:28 上午
 * @Descripton: 自定非受检异常
 **/
public class IllegalParamsException extends RuntimeException implements WithTypeException{

    //异常类型
    private Type type;

    public IllegalParamsException(Type type, String msg){
        super(msg);
        this.type = type;
    }

    public Type type(){
        return type;
    }

    public enum Type{
        WRONG_PAGE_NUM,WRONG_TYPE
    }
}
