package com.o0u0o.house.user.common;

import lombok.Data;

/**
 * 响应结果
 * @Author aiuiot
 * @Date 2019/12/29 10:23 下午
 * @Descripton:
 **/
@Data
public class RestResponse<T> {
    private int code;
    private String msg;
    private T result;   //返回结果

    public static <T> RestResponse<T> success(){
        RestResponse<T> response = new RestResponse<>();
        return response;
    }

    public static <T> RestResponse<T> success(T result){
        RestResponse<T> response = new RestResponse<>();
        response.setResult(result);
        return response;
    }

    public static <T> RestResponse<T> error(RestCode code){
        return new RestResponse<>(code.code, code.msg);
    }

    public RestResponse(){
        this(RestCode.OK.code, RestCode.OK.msg);
    }

    public RestResponse(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
