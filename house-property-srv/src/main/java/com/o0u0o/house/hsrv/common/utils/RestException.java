package com.o0u0o.house.hsrv.common.utils;

/**
 * <h1>RestException异常处理</h1>
 * @author mac
 * @date 2022/3/9 9:16 PM
 */
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
