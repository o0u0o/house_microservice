package com.o0u0o.house.hsrv.common.exception;

/**
 * <h1>IllegalParamsException</h1>
 * @author o0u0o
 * @date 2022/3/9 7:30 PM
 */
public class IllegalParamsException extends RuntimeException implements WithTypeException {

    private static final long serialVersionUID = 936915964862903634L;

    private Type type;

    public IllegalParamsException(Type type, String message) {
        super(message);
        this.type = type;
    }

    public  Type type(){
        return type;
    }


    public enum Type{
        WRONG_PAGE_NUM;
    }
}
