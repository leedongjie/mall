package com.decade.mall.ums.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.decade.mall.ums.mapper")
public class MyBatisPlusConfig {
}
