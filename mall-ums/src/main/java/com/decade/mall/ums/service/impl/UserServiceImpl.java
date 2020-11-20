package com.decade.mall.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.decade.mall.ums.entity.User;
import com.decade.mall.ums.mapper.UserMapper;
import com.decade.mall.ums.security.service.MyUserDetailsService;
import com.decade.mall.ums.security.util.JwtTokenUtil;
import com.decade.mall.ums.service.UserService;
import com.decade.mall.ums.util.CommonResult;
import com.decade.mall.ums.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李东杰
 * @since 2020-11-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, MyUserDetailsService myUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    public CommonResult login(String username, String password) {
        try {
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                HashMap<String, Object> map = new HashMap<>();
                map.put("username", userDetails.getUsername());
                String token = JwtTokenUtil.generateToken(map);
                return CommonResult.success("登录成功", token);
            } else {
                return CommonResult.failure(ResultCodeEnum.USER_PASSWORD_ERROR);
            }

        } catch (UsernameNotFoundException usernameNotFoundException) {
            return CommonResult.failure(ResultCodeEnum.USER_ACCOUNT_NOT_EXIST);
        }
    }
}
