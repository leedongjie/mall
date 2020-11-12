package com.decade.mall.ums.service.impl;

import com.decade.mall.ums.entity.Admin;
import com.decade.mall.ums.mapper.AdminMapper;
import com.decade.mall.ums.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author decade
 * @since 2020-11-12
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
