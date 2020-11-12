package com.decade.mall.ums.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台用户角色和权限关系表
 * </p>
 *
 * @author decade
 * @since 2020-11-12
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("ums_role_permission_relation")
@ApiModel(value="RolePermissionRelation对象", description="后台用户角色和权限关系表")
public class RolePermissionRelation extends Model<RolePermissionRelation> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private Long roleId;

    private Long permissionId;


    @Override
    protected Serializable pkVal() {
          return this.id;
      }

}
