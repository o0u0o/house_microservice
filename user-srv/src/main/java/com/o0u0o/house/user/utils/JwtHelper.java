package com.o0u0o.house.user.utils;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;
import java.util.Map;

/**
 * @Author aiuiot
 * @Date 2020/1/13 3:17 下午
 * @Descripton: Jwt工具类 生成token 校验token
 **/
public class JwtHelper {

    //定义一个 SECRET
    private static final String SECRET = "session_secret";

    //发布者
    private static final String ISSUER = "aiuiot_user";

    /**
     * 生成token
     * @param claims
     * @return
     */
    public static String genToken(Map<String, String> claims){
        //使用 HMAC256 算法
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTCreator.Builder builder = JWT.create().withIssuer(ISSUER) //创建发布者
                    .withExpiresAt(DateUtils.addDays(new Date(), 1));   //设置过期时间为1天

            claims.forEach((k, v) -> builder.withClaim(k, v));
            return builder.sign(algorithm).toString();
        } catch (IllegalArgumentException | UnsupportedOperationException e){
            //抛出非受检异常
            throw new RuntimeException(e);
        }

    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static Map<String, String> verifyToken(String token){
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256(SECRET);

        } catch (IllegalArgumentException | UnsupportedOperationException e){
            throw new RuntimeException(e);
        }
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> map = jwt.getClaims();
        Map<String, String> resultMap = Maps.newHashMap();
        map.forEach((k, v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }
}
