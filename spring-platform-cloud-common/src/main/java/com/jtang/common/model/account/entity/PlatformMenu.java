package com.jtang.common.model.account.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 菜单表
* @author jtang
* @date 2020-06-30
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PlatformMenu对象", description="菜单表")
public class PlatformMenu implements Serializable {
    private Long id;

    @ApiModelProperty(value = "菜单编码")
    private String code;

    @ApiModelProperty(value = "父菜单ID")
    private Long pId;

    @ApiModelProperty(value = "名称")
    private String menuName;

    @ApiModelProperty(value = "请求地址")
    private String url;

    @ApiModelProperty(value = "是否是菜单")
    private String isMenu;

    @ApiModelProperty(value = "菜单层级")
    private Integer level;

    @ApiModelProperty(value = "菜单排序")
    private Integer sort;

    @ApiModelProperty(value = "菜单状态")
    private String status;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
