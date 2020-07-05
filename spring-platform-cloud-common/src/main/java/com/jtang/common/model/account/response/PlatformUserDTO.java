package com.jtang.common.model.account.response;

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

    @ApiModelProperty(value = "用户角色")
    private List<String> roleName;


}
