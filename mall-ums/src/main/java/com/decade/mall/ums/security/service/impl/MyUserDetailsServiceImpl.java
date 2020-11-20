package com.decade.mall.ums.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.decade.mall.ums.security.entity.MyUserDetails;
import com.decade.mall.ums.entity.Permission;
import com.decade.mall.ums.entity.User;
import com.decade.mall.ums.mapper.PermissionMapper;
import com.decade.mall.ums.mapper.UserMapper;
import com.decade.mall.ums.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

    private final UserMapper userMapper;

    private final PermissionMapper permissionMapper;

    @Autowired
    public MyUserDetailsServiceImpl(UserMapper userMapper, PermissionMapper permissionMapper) {
        this.userMapper = userMapper;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            List<Permission> permissions = permissionMapper.listPermissionsByUsername(username);
            return new MyUserDetails(user, permissions);
        }
        throw new UsernameNotFoundException("用户名不存在");
    }

}
