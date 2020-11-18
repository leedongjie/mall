package com.decade.mall.ums.config;

import com.decade.mall.ums.component.RestfulAuthenticationFailureHandler;
import com.decade.mall.ums.component.RestfulAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * SpringSecurity的配置
 * 使用@EnableGlobalMethodSecurity注解可以解锁@PreAuthorize @PostAuthorize
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AdminUserDetailsService adminUserDetailsService;

    @Autowired
    private RestfulAuthenticationSuccessHandler restfulAuthenticationSuccessHandler;

    @Autowired
    private RestfulAuthenticationFailureHandler restfulAuthenticationFailureHandler;

    @Autowired
    public SpringSecurityConfig(AdminUserDetailsService adminUserDetailsService) {
        this.adminUserDetailsService = adminUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/swagger-resources/**",
//                        "/v2/api-docs/**"
//                ).permitAll()   // 不拦截静态资源以及swagger
//                .antMatchers("/admin/login", "/admin/register")// 对登录注册要允许匿名访问
//                .permitAll()
//                .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
//                .permitAll()
//                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
//                .authenticated()
//                .and()
//                .formLogin()// 开启SpringSecurity默认的登录界面
//                .successHandler(restfulAuthenticationSuccessHandler) // 认证成功后的处理逻辑
//                .failureHandler(restfulAuthenticationFailureHandler); // 认证失败后的处理逻辑


//        http.authorizeRequests()
//                .antMatchers("/admin/**")
//                .hasRole("r1")
//                .and()

        http.authorizeRequests().anyRequest().authenticated();

        http.formLogin()// 开启SpringSecurity默认的登录界面
                .successHandler(restfulAuthenticationSuccessHandler) // 认证成功后的处理逻辑
                .failureHandler(restfulAuthenticationFailureHandler); // 认证失败后的处理逻辑
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
