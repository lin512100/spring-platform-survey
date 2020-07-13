package com.jtang.common.model.account.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 操作DTO
 * @date 2020/7/11 18:33
 * @author LinJinTang
 */
@Getter
@Setter
@ToString
public class PlatformPermissionDTO {

    private Long id;

    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "功能描述")
    private String func;

    @ApiModelProperty(value = "方法名")
    private String method;

}
