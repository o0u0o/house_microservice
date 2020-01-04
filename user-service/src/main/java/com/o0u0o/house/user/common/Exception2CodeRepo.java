package com.o0u0o.house.user.common;

import com.google.common.collect.ImmutableMap;
import com.o0u0o.house.user.exception.IllegalParamsException;
import com.o0u0o.house.user.exception.WithTypeException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.reflect.FieldUtils;

/**
 * @Author aiuiot
 * @Date 2020/1/4 10:22 上午
 * @Descripton: 异常转为code
 **/
public class Exception2CodeRepo {

    private static final ImmutableMap<Object, RestCode> MAP = ImmutableMap.<Object, RestCode>builder()
            .put(IllegalParamsException.Type.WRONG_PAGE_NUM, RestCode.WRONG_PAGE)
            .put(IllegalStateException.class, RestCode.UNKOWN_RROR).build();

    //利用反射机制 读取
    private static Object getType(Throwable throwable){
        try {
            return FieldUtils.readDeclaredField(throwable, "type", true);
        } catch (Exception e){
            return null;
        }
    }

    public static RestCode getCode(Throwable throwable){
        if (throwable == null){
            return RestCode.UNKOWN_RROR;
        }
        Object target = throwable;
        if (throwable instanceof WithTypeException){
            Object type = getType(throwable);
            if (type != null){
                target = type;
            }
        }

        RestCode restCode = MAP.get(target);
        if (restCode != null){
            return restCode;
        }
        Throwable rootCause = ExceptionUtils.getRootCause(throwable);
        if (rootCause != null){
            return getCode(rootCause);
        }
        return RestCode.UNKOWN_RROR;
    }

}
