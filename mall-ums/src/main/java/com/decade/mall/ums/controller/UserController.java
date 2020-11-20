package com.decade.mall.ums.controller;

import com.decade.mall.ums.entity.User;
import com.decade.mall.ums.service.UserService;
import com.decade.mall.ums.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李东杰
 * @since 2020-11-18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据前端传递的用户名与密码进行登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 成功则返回token, 失败则返回原因
     */
    @PostMapping("/login")
    public CommonResult login(@RequestParam String username,
                              @RequestParam String password) {
        // 需要加入校验逻辑
        return userService.login(username, password);
    }

    @GetMapping("/listUsers")
    @PreAuthorize("hasAuthority('ums:user:listUsers')")
    public CommonResult listUsers() {
        List<User> users = userService.list();
        return CommonResult.success(users);
    }

    @GetMapping("/insert")
    @PreAuthorize("hasAuthority('ums:user:insert')")
    public CommonResult insert() {
        return CommonResult.success("Hello");
    }

}

