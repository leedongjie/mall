package com.decade.mall.ums.config;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.lang.JoseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    /**
     * 生成全局公钥与私钥
     *
     * @return 公钥与私钥
     */
    @Bean
    public RsaJsonWebKey rsaJsonWebKey() throws JoseException {
        RsaJsonWebKey rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
        rsaJsonWebKey.setKeyId("k1");
        return rsaJsonWebKey;
    }

}
