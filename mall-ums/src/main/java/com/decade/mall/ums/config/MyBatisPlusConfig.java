package com.decade.mall.ums.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 扫描mapper接口,并生成代理对象
 */
@Configuration
@MapperScan("com.decade.mall.ums.mapper")
public class MyBatisPlusConfig {

}
