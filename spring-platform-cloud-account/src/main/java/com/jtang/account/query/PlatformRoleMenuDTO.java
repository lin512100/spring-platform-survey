package com.jtang.account.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @date 2020/7/14 21:09
 * @author LinJinTang
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlatformRoleMenuDTO {

    @ApiModelProperty(value = "权限ID")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID")
    private List<Long> menuId;
}
