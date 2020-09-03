package com.jtang.make.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 资源信息
* @author jtang
* @date 2020-09-03
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TbResourceInfo对象", description="资源信息")
public class TbResourceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源信息")
    private String info;

    @ApiModelProperty(value = "资源访问地址")
    private String url;

    @ApiModelProperty(value = "资源访问密码")
    private String pwd;

    @ApiModelProperty(value = "资源来源途径")
    private String source;

    @ApiModelProperty(value = "资源类型")
    private String type;


}
