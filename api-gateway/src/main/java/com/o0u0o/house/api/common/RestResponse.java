package com.o0u0o.house.api.common;

import lombok.Data;

/**
 * @Author aiuiot
 * @Date 2019/12/29 10:23 下午
 * @Descripton: 响应结果
 **/
@Data
public class RestResponse<T> {

    /** 相应码 */
    private int code;

    /** 相应消息 */
    private String msg;

    /** 返回结果 */
    private T result;

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
