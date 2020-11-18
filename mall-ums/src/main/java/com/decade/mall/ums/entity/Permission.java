package com.decade.mall.ums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 李东杰
 * @since 2020-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_permission")
@ApiModel(value = "Permission对象", description = "")
public class Permission extends Model<Permission> {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "权限ID")
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "权限名字")
  private String name;

  @ApiModelProperty(value = "权限描述")
  private String description;

  @ApiModelProperty(value = "权限图标")
  private String icon;

  @ApiModelProperty(value = "权限类型")
  private String type;

  @ApiModelProperty(value = "前端uri")
  private String uri;

  @ApiModelProperty(value = "是否启用")
  private Integer enable;

  @ApiModelProperty(value = "创建时间")
  private LocalDateTime gmtCreate;

  @ApiModelProperty(value = "上次修改时间")
  private LocalDateTime gmtModified;


  @Override
  protected Serializable pkVal() {
    return this.id;
  }

}
