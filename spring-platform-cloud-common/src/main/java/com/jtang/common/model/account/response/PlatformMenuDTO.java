package com.jtang.common.model.account.response;

import com.jtang.common.model.account.entity.PlatformMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * 菜单列表
 * @author linjt
 * @date 2020/7/13
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlatformMenuDTO extends PlatformMenu{

    @ApiModelProperty(name = "pidMenuName", value = "父菜单名称")
    private String pidMenuName;

    @ApiModelProperty(name = "child", value = "子树")
    private List<PlatformMenuDTO> children;


}
