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
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)
 * </p>
 *
 * @author decade
 * @since 2020-11-12
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("ums_admin_permission_relation")
@ApiModel(value="AdminPermissionRelation对象", description="后台用户和权限关系表(除角色中定义的权限以外的加减权限)")
public class AdminPermissionRelation extends Model<AdminPermissionRelation> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private Long adminId;

    private Long permissionId;

    private Integer type;


    @Override
    protected Serializable pkVal() {
          return this.id;
      }

}
