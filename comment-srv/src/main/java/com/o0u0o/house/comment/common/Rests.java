package com.o0u0o.house.comment.common;

import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.reflect.FieldUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

/**
 * <h1>Rests类</h1>
 * @author o0u0o
 * @date 2022/3/16 10:53 AM
 */
public final class Rests {

    private Rests(){};

    private static final Logger logger = LoggerFactory.getLogger(Rests.class);

    private static DefaultHandler defaultHandler = new DefaultHandler();

    public static  <T> T exc(RestFunction<T> call){
        return exc(call, defaultHandler, false);
    }

    public static  <T> T exc(RestFunction<T> call,ResultHandler resultHandler){
        return exc(call, resultHandler, false);
    }

    public static  <T> T excWithRetry(RestFunction<T> call){
        return exc(call, defaultHandler, true);
    }

    public static <T> T exc(RestFunction<T> call, ResultHandler resultHandler, boolean retry) {
        T result = sendReq(call, retry);
        return resultHandler.handle(result);
    }

    public static String toUrl(String serviceName,String path){
        return "http://" + serviceName + path;
    }


    public static class DefaultHandler implements ResultHandler{

        @Override
        public <T> T handle(T result) {
            int code = 1;
            String msg = "";
            try {
                code =  (Integer) FieldUtils.readDeclaredField(result, "code", true);
                msg =  (String)FieldUtils.readDeclaredField(result, "msg", true);
            } catch (Exception e) {
                //ignore
            }
            if (code != 0) {
                throw new RestException("Get erroNo " + code + " when execute rest call with errorMsg " +msg);
            }
            return result;
        }

    }

    public interface  ResultHandler{
        <T> T handle(T result);
    }

    public static <T> T sendReq(RestFunction<T> call, boolean retry) {
        T result = null;
        try {
            if (retry) {
                result = Retrys.retry(call);
            } else {
                result = call.call();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RestException("Send request error when execute rest call with params " + call.getParams());
        }finally{
            logger.info("Rest executed with params={} and result={}",call.getParams(),result==null?"":result.toString());
        }
        return result;
    }

    public static abstract class RestFunction<T> implements Callable<T> {
        private Map<String,Object> paramsMap = Maps.newHashMap();
        public  Map<String,Object> getParams(){
            return paramsMap;
        }
        public void withParams(Map<String,Object> toAddparams){
            paramsMap.putAll(toAddparams);
        }
    }
}
