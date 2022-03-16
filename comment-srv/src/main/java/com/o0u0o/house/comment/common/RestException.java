package com.o0u0o.house.comment.common;

/**
 * <h1>RestException</h1>
 * @author o0u0o
 * @date 2022/3/16 10:54 AM
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
