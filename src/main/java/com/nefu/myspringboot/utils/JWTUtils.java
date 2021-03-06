package com.nefu.myspringboot.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private static final String jwtToken = "123456nefu@0919";

    //生成令牌token
    public static String createToken(Long userId) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken)//签发算法，密钥为jwtToken
                .setClaims(claims) //body数据，要唯一，自行设置
                .setIssuedAt(new Date())//设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 60 * 1000));//一天的有效时间
        String token = jwtBuilder.compact();
        return token;
    }

    //检测token是否合法
    public static Map<String, Object> checkToken(String token) {
        try {
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String, Object>) parse.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
   1. token的合法校验  jwt 可以把token 放在redis 设置有效时间
    是否为空  解析是否成功  redis是否存在
    2. 如果校验失败 返回错误
    3.如果成功，返回对应的结果 LoginUserVo
     */


}
