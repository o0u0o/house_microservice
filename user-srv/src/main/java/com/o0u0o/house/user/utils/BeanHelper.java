package com.o0u0o.house.user.utils;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.beans.PropertyDescriptor;
import java.util.Date;


/**
 * @Author aiuiot
 * @Date 2020/1/13 10:19 上午
 * @Descripton: Bean助手
 **/
public class BeanHelper {
    private static final String updateTimeKey  = "updateTime";

    private static final String createTimeKey  = "createTime";


    public static <T> void setDefaultProp(T target,Class<T> clazz) {
        PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(clazz);
        for (PropertyDescriptor propertyDescriptor : descriptors) {
            String fieldName = propertyDescriptor.getName();
            Object value;
            try {
                value = PropertyUtils.getProperty(target,fieldName );
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException("can not set property  when get for "+ target +" and clazz "+clazz +" field "+ fieldName);
            }
            if (String.class.isAssignableFrom(propertyDescriptor.getPropertyType()) && value == null) {
                try {
                    PropertyUtils.setProperty(target, fieldName, "");
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException("can not set property when set for "+ target +" and clazz "+clazz + " field "+ fieldName);
                }
            }else if (Number.class.isAssignableFrom(propertyDescriptor.getPropertyType()) && value == null) {
                try {
                    BeanUtils.setProperty(target, fieldName, "0");
                } catch (Exception e) {
                    throw new RuntimeException("can not set property when set for "+ target +" and clazz "+clazz + " field "+ fieldName);
                }
            }
        }
    }

    /**
     * 更新时
     * @param target
     * @param <T>
     */
    public static <T> void onUpdate(T target){
        try {
            PropertyUtils.setProperty(target, updateTimeKey, System.currentTimeMillis());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return;
        }
    }

    /**
     * 插入是自动设置创建时间和更新时间
     * @param target
     * @param <T>
     */
    public static <T> void onInsert(T target){
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        try {
            PropertyUtils.setProperty(target, updateTimeKey, date);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }

        try {
            PropertyUtils.setProperty(target, createTimeKey, date);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }
    }
}
