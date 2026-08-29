package com.o0u0o.house.api.common.zuul;

import com.alibaba.fastjson2.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.o0u0o.house.api.common.ResultMsg;
import com.o0u0o.house.api.common.exception.RateLimiterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * <h2>错误Filter</h2>
 * Gateway 全局异常处理器（替代 Zuul 的 ErrorFilter）
 * @date 2022/3/15 3:32 PM
 */
@Component
@Order(-1)
public class ErrorFilter implements WebExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

        logger.error("Exception {} was thrown in gateway filters: ", ex.getMessage(), ex);

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        ResultMsg resultMsg = ResultMsg.errorMsg("系统错误");
        if (ex instanceof RateLimiterException || ex.getCause() instanceof RateLimiterException) {
            resultMsg.setErrorMsg("系统繁忙,稍后重试");
        }

        byte[] bytes = JSON.toJSONString(resultMsg).getBytes();
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(buffer));
    }
}
