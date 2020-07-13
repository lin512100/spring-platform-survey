package com.jtang.common.model.account.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单树状图
 * @date 2020/7/12 1:15
 * @author LinJinTang
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MenuTree implements Serializable {


    @ApiModelProperty(name = "id",value = "菜单ID")
    private Long id;

    @ApiModelProperty(name = "menuName" , value = "名称")
    private String menuName;

    @ApiModelProperty(name = "pid",value = "父级ID")
    private Long pid;

    @ApiModelProperty(name = "choice", value = "是否被选择")
    private Integer choice;

    @ApiModelProperty(name = "child", value = "子树")
    private List<MenuTree> children;


}
