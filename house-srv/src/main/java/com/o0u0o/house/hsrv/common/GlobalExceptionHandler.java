package com.o0u0o.house.hsrv.common;

import com.o0u0o.house.hsrv.common.exception.Exception2CodeMap;
import com.o0u0o.house.hsrv.common.exception.WithTypeException;
import org.apache.commons.lang.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>全局异常处理</h1>
 * @author o0u0o
 * @date 2022/3/9 7:24 PM
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER  = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public RestResponse<Object> handler(HttpServletRequest req, Throwable throwable) throws Exception {
        LOGGER.error(throwable.getMessage(), throwable);
        LOGGER.error(req.getRequestURL().toString() + " encounter exception or error");
        Object target = throwable;
        if (throwable instanceof WithTypeException) {
            Object type = getType(throwable);
            if (type != null) {
                target = type;
            }
        }
        RestCode code = Exception2CodeMap.getCode(target);
        RestResponse<Object> response = new RestResponse<>(code.code,code.msg);
        return response;
    }

    private Object getType(Throwable throwable){
        try {
            return FieldUtils.readDeclaredField(throwable, "type", true);
        } catch (Exception e) {
            //ignore
            return null;
        }
    }
}
