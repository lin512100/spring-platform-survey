package com.jtang.bussiness.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 菜单
* @author jtang
* @date 2020-06-02
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Menu对象", description="菜单")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "级别")
    private Integer level;

    @ApiModelProperty(value = "优先级排序1")
    private Integer sort;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "备注")
    private String remark;


}
