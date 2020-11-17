package com.decade.mall.ums.controller;


import com.decade.mall.ums.entity.Admin;
import com.decade.mall.ums.service.AdminService;
import com.decade.mall.ums.util.Result;
import com.decade.mall.ums.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author decade
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "AdminController", value = "管理员管理")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "获取所有管理员", response = Admin.class)
    public Result findAll() {
        List<Admin> list = adminService.list();
        return Result.success(ResultCodeEnum.SUCCESS, list);
    }
}

