package com.decade.mall.ums.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台用户权限表
 * </p>
 *
 * @author decade
 * @since 2020-11-12
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("ums_permission")
@ApiModel(value="Permission对象", description="后台用户权限表")
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty(value = "父级权限id")
      private Long pid;

      @ApiModelProperty(value = "名称")
      private String name;

      @ApiModelProperty(value = "权限值")
      private String value;

      @ApiModelProperty(value = "图标")
      private String icon;

      @ApiModelProperty(value = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）")
      private Integer type;

      @ApiModelProperty(value = "前端资源路径")
      private String uri;

      @ApiModelProperty(value = "启用状态；0->禁用；1->启用")
      private Integer status;

      @ApiModelProperty(value = "创建时间")
      private LocalDateTime createTime;

      @ApiModelProperty(value = "排序")
      private Integer sort;


    @Override
    protected Serializable pkVal() {
          return this.id;
      }

}
