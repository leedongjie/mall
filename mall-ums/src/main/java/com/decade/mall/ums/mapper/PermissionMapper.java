package com.decade.mall.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.decade.mall.ums.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 李东杰
 * @since 2020-11-18
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> listPermissionsByUsername(String username);

}
