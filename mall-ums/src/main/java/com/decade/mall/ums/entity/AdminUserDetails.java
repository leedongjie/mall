//package com.decade.mall.ums.entity;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * 使用SpringSecurity必须的类
// * 主要包含了获取用户权限列表,用户是否过期等方法
// */
//public class AdminUserDetails implements UserDetails {
//
//    private final Admin admin;
//
//    private final List<Permission> permissionList;
//
//    public AdminUserDetails(Admin admin, List<Permission> permissionList) {
//        this.admin = admin;
//        this.permissionList = permissionList;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        //返回当前用户的权限
//        return permissionList.stream()
//                .filter(permission -> permission.getValue() != null)
//                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public String getPassword() {
//        return admin.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return admin.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
