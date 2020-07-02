package com.jtang.common.model.oauth.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 用户
* @author jtang
* @date 2020-06-30
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PlatformUser对象", description="用户")
public class PlatformUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "盐值")
    private String salt;

    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "头像")
    private String userpic;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "身份证")
    private String identity;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "用户状态")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
