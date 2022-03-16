package com.o0u0o.house.comment.common;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * <h1>Bean帮助类</h1>
 * @author o0u0o
 * @date 2022/3/16 10:39 AM
 */
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

    public static <T> void onUpdate(T target){
        try {
            PropertyUtils.setProperty(target, updateTimeKey, System.currentTimeMillis());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return;
        }
    }

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
