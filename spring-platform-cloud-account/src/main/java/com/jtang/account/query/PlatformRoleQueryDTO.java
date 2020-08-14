package com.jtang.account.query;

import com.jtang.common.base.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 角色 查询类
* @author lin512100
* @since 2020-06-30
* @version v1.0
*/
@Getter
@Setter
@ToString
public class PlatformRoleQueryDTO extends BaseQuery {

    @ApiModelProperty(value = "角色名称")
    private String roleName;
}
