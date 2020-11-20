package com.decade.mall.ums.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Map;

public class JwtTokenUtil {

    /**
     * 根据payload生成token
     *
     * @param claims payload
     * @return token
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, "Secret")
                .compact();
    }

    /**
     * 验签
     *
     * @param token token
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            Jwts.parser()
                    .setSigningKey("Secret")
                    .parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey("Secret")
                .parseClaimsJws(token).getBody();
    }

}
