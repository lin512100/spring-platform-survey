package com.jtang.resource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
* @date 2020-09-07
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_resource_info")
@ApiModel(value="TbResourceInfo对象", description="资源信息")
public class TbResourceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "资源序列号")
    private String uuid;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源信息")
    private String info;

    @ApiModelProperty(value = "资源类型")
    private String type;

    @ApiModelProperty(value = "标题")
    private String title;


}
