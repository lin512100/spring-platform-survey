package com.jtang.account.query;

import com.jtang.common.base.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 权限 查询类
* @author lin512100
* @since 2020-06-30
* @version v1.0
*/
@Getter
@Setter
@ToString
public class PlatformPermissionQueryDTO extends BaseQuery {

    @ApiModelProperty(name = "menuName",value = "菜单名")
    private String menuName;

    @ApiModelProperty(name = "func",value = "方法名")
    private String func;
}
