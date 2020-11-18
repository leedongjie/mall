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
@TableName("ums_user")
@ApiModel(value = "User对象", description = "")
public class User extends Model<User> {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "用户ID")
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "用户名")
  private String username;

  @ApiModelProperty(value = "密码")
  private String password;

  @ApiModelProperty(value = "盐")
  private String passwordSalt;

  @ApiModelProperty(value = "头像")
  private String avatar;

  @ApiModelProperty(value = "昵称")
  private String nickName;

  @ApiModelProperty(value = "电子邮箱")
  private String email;

  @ApiModelProperty(value = "上次登录时间")
  private LocalDateTime lastLoginTime;

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
