package com.o0u0o.house.hsrv.config;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <h1>GenericRest实现类</h1>
 * 即支持直连又支持服务发现的rest调用
 * @author o0u0o
 * @date 2022/3/9 9:11 PM
 */
@Service
public class GenericRest {

    @Autowired
    private RestTemplate lbRestTemplate;

    @Autowired
    private RestTemplate directRestTemplate;

    private static final  String directFlag = "direct://";

    /**
     * <h2>post请求</h2>
     * @param url
     * @param reqBody
     * @param responseType
     * @param <T>
     * @return
     */
    public <T> ResponseEntity<T> post(String url, Object reqBody, ParameterizedTypeReference<T> responseType){
        RestTemplate template = getRestTemplate(url);
        url = url.replace(directFlag, "");
        return template.exchange(url, HttpMethod.POST, new HttpEntity(reqBody), responseType);
    }

    public <T> ResponseEntity<T> get(String url, ParameterizedTypeReference<T> responseType){
        RestTemplate template = getRestTemplate(url);
        url = url.replace(directFlag, "");
        return template.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, responseType);
    }

    private RestTemplate getRestTemplate(String url){
        if (url.contains(directFlag)){
            return directRestTemplate;
        }else {
            return lbRestTemplate;
        }
    }
}
