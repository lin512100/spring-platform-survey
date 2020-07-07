package com.jtang.common.model.account.response;

import com.jtang.base.enums.SexEnums;
import com.jtang.base.enums.UserStatusEnums;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 返回实体类
 * @date 2020/7/5 17:28
 * @author LinJinTang
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlatformUserDTO {
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "身份证")
    private String identity;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "用户状态")
    private Integer status;

    @ApiModelProperty(value = "性别描述")
    private String sexDesc;

    @ApiModelProperty(value = "用户状态描述")
    private String statusDesc;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "用户角色")
    private String roleName;

    @ApiModelProperty(value = "角色ID")
    private String roleId;

    public String getStatusDesc(){
        return UserStatusEnums.getMsg(status).getMsg();
    }

    public String getSexDesc(){
        return SexEnums.getMsg(sex).getMsg();
    }



}
