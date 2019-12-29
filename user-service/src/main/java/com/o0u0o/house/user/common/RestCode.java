package com.o0u0o.house.user.common;

import lombok.Getter;

/**
 * 返回代码
 */
@Getter
public enum RestCode {
    OK(0, "成功"),

    UNKOWN_RROR(1, "服务异常"),

    WRONG_PAGE(10100, "页码不存在")

    ;

    //响应码
    public final int code;

    //消息
    public final String msg;

    RestCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
