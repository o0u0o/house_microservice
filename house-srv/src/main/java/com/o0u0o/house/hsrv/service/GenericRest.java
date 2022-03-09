package com.o0u0o.house.hsrv.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

/**
 * <h1>GenericRest接口</h1>
 * @author o0u0o
 * @date 2022/3/9 9:10 PM
 */
public interface GenericRest {

    /**
     * <h1>post请求方式</h1>
     * @param url
     * @param reqBody
     * @param responseType
     * @param <T>
     * @return
     */
    public <T> ResponseEntity<T> post(String url, Object reqBody, ParameterizedTypeReference<T> responseType);

    /**
     * <h1>get请求方式</h1>
     * @param url
     * @param responseType
     * @param <T>
     * @return
     */
    public <T> ResponseEntity<T> get(String url, ParameterizedTypeReference<T> responseType);
}
