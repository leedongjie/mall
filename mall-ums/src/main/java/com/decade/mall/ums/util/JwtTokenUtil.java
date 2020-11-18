package com.decade.mall.ums.util;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * JwtToken生成的工具类
 */
@Component
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
//    @Value("${jwt.secret}")
//    private String secret;
//    @Value("${jwt.expiration}")
//    private Long expiration;

    @Autowired
    private RsaJsonWebKey rsaJsonWebKey;

    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) throws JoseException {
        JwtClaims jwtClaims = new JwtClaims();
        jwtClaims.setExpirationTimeMinutesInTheFuture(10);
        // 加入负载
        claims.forEach(jwtClaims::setClaim);
        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(jwtClaims.toJson());
        jws.setKey(rsaJsonWebKey.getPrivateKey());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
        return jws.getCompactSerialization();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private JwtClaims getClaimsFromToken(String token) {
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setMaxFutureValidityInMinutes(300) // but the  expiration time can't be too crazy
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setVerificationKey(rsaJsonWebKey.getKey()) // verify the signature with the public key
                .build(); // create the JwtConsumer instance
        JwtClaims jwtClaims = null;
        try {
            jwtClaims = jwtConsumer.processToClaims(token);
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}", token);
        }
        return jwtClaims;
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            JwtClaims jwtClaims = getClaimsFromToken(token);
            username = jwtClaims.getClaimValue("username", String.class);
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        JwtClaims claims = getClaimsFromToken(token);
        return null;
    }

//    /**
//     * 根据用户信息生成token
//     */
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
//        claims.put(CLAIM_KEY_CREATED, new Date());
//        return generateToken(claims);
//    }

    /**
     * 判断token是否可以被刷新
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

//    /**
//     * 刷新token
//     */
//    public String refreshToken(String token) {
//        Claims claims = getClaimsFromToken(token);
//        claims.put(CLAIM_KEY_CREATED, new Date());
//        return generateToken(claims);
//    }
}
