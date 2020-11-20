package com.decade.mall.ums.config;

import com.decade.mall.ums.security.filter.JwtAuthorizationTokenFilter;
import com.decade.mall.ums.security.handler.RestfulAccessDeniedHandler;
import com.decade.mall.ums.security.handler.RestfulAuthenticationEntryPoint;
import com.decade.mall.ums.security.handler.RestfulAuthenticationFailureHandler;
import com.decade.mall.ums.security.handler.RestfulAuthenticationSuccessHandler;
import com.decade.mall.ums.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * SpringSecurity的配置
 * 使用@EnableGlobalMethodSecurity注解可以解锁@PreAuthorize @PostAuthorize 进行方法级别的权限
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;

    private final RestfulAuthenticationSuccessHandler restfulAuthenticationSuccessHandler;

    private final RestfulAuthenticationFailureHandler restfulAuthenticationFailureHandler;

    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    private final RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;

    @Autowired
    public SpringSecurityConfig(MyUserDetailsService myUserDetailsService,
                                RestfulAuthenticationSuccessHandler restfulAuthenticationSuccessHandler,
                                RestfulAuthenticationFailureHandler restfulAuthenticationFailureHandler,
                                RestfulAccessDeniedHandler restfulAccessDeniedHandler,
                                RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint) {
        this.myUserDetailsService = myUserDetailsService;
        this.restfulAuthenticationSuccessHandler = restfulAuthenticationSuccessHandler;
        this.restfulAuthenticationFailureHandler = restfulAuthenticationFailureHandler;
        this.restfulAccessDeniedHandler = restfulAccessDeniedHandler;
        this.restfulAuthenticationEntryPoint = restfulAuthenticationEntryPoint;
    }

    @Autowired
    private JwtAuthorizationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 一旦这里写东西,下面必须有passwordEncoder
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/login")
                .permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler) // 访问被拒绝,没有权限
                .authenticationEntryPoint(restfulAuthenticationEntryPoint); // 没有登录或者Token过期
    }

    /**
     * 密码编码器
     * 实际开发中,并不会对数据库密码进行解密,而是将传入的密码加密后,再与数据库进行比对.
     *
     * @return 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
