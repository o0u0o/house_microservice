package com.o0u0o.house.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


/**
 * <h1>既支持直连又支持服务发现的调用</h1>
 * @Author aiuiot
 * @Date 2019/12/29 9:43 下午
 * @Descripton: 为什么要做二次封装 即支持直连又支持服务发现的rest调用
 * 因为 RestTemplate 在 Spring 5 已标记为 deprecated，推荐用 WebClient
 **/
@Service
public class GenericRest {

    @Autowired
    private RestClient lbRestClient;

    @Autowired
    private RestClient directRestClient;

    /** 直连请求 */
    private static final String directFlag = "direct://";


    public <T> ResponseEntity<T> post(String url, Object reqBody, ParameterizedTypeReference<T> responseType){
        RestClient client = getClient(url);
        String actualUrl = url.replace(directFlag, "");

        return client.post()
                .uri(actualUrl)
                .body(reqBody)
                .retrieve()
                .toEntity(responseType);
    }

    public <T> ResponseEntity<T> get(String url, ParameterizedTypeReference<T> responseType){
        RestClient client = getClient(url);
        String actualUrl = url.replace(directFlag, "");
        return client.get()
                .uri(actualUrl)
                .retrieve()
                .toEntity(responseType);
    }


    private RestClient getClient(String url) {
        return url.contains(directFlag) ? directRestClient : lbRestClient;
    }
}
