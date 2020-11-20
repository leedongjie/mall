package com.decade.mall.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.decade.mall.ums.entity.User;
import com.decade.mall.ums.util.CommonResult;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 李东杰
 * @since 2020-11-18
 */
public interface UserService extends IService<User> {

    CommonResult login(String username, String password);

}
