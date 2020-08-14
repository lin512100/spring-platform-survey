package com.jtang.common.model.account.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 
* @author lin512100
* @date 2020-07-11
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PlatformRoleMenu对象", description="")
public class PlatformRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID")
    private Long  menuId;

    @ApiModelProperty(value = "操作权限")
    private String operate;

    public PlatformRoleMenu(Long roleId ,Long menuId){
        this.roleId = roleId;
        this.menuId = menuId;
    }


}
