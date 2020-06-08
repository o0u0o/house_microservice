package com.o0u0o.house.user.utils;

import com.google.common.base.Throwables;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @Author aiuiot
 * @Date 2020/1/7 10:42 上午
 * @Descripton:
 **/
public class HashUtils {
    private static final HashFunction FUNCTION = Hashing.md5();

    private static final HashFunction MURMUR_FUNC = Hashing.murmur3_128();

    private static final String SALT = "aiuiot.com";

    /**
     * MD5 + 盐加密
     * @param password
     * @return
     */
    public static String encryPassword(String password){
        HashCode code = FUNCTION.hashString(password + SALT, Charset.forName("UTF-8"));
        return code.toString();
    }

    /**
     * 转为String
     * @param input
     * @return
     */
    public static String hashString(String input){
        HashCode code = null;
        try {
            code = MURMUR_FUNC.hashBytes(input.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            Throwables.propagate(e);
        }
        return code.toString();
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(encryPassword("111111"));
    }
}
