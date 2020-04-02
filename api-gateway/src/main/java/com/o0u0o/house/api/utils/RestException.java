package com.o0u0o.house.api.utils;

/**
 * @Author aiuiot
 * @Date 2020/4/2 10:26 下午
 * @Descripton: 自定义异常
 **/
public class RestException extends RuntimeException {

    private static final long serialVersionUID = -3760708879601335491L;

    public RestException(String errorCode) {
        super(errorCode);
    }

    public RestException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public RestException(String errorCode, String errorMsg) {
        super(errorCode + ":" + errorMsg);
    }

    public RestException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode + ":" + errorMsg, cause);
    }

    public RestException(Throwable cause) {
        super(cause);
    }
}
