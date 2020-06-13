package com.jtang.account.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 权限
* @author jtang
* @date 2020-06-13
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TbPermission对象", description="权限")
public class TbPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限URL")
    private String url;

    @ApiModelProperty(value = "操作权限")
    private String operate;

    @ApiModelProperty(value = "图标")
    private String icon;


}
