package com.jtang.account.query;
import com.jtang.common.base.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 菜单表 查询类
* @author jtang
* @since 2020-06-30
* @version v1.0
*/
@Getter
@Setter
@ToString
public class PlatformMenuQueryDTO extends BaseQuery {

    @ApiModelProperty(name = "menuName" , value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "是否是菜单")
    private Integer isMenu;

    @ApiModelProperty(value = "父菜单ID")
    private Long pid;

    @ApiModelProperty(value = "地址")
    private String url;

    @ApiModelProperty(value = "方法名")
    private String method;

}
