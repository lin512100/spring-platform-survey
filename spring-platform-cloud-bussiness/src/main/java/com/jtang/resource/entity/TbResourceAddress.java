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
* 资源地址
* @author jtang
* @date 2020-09-07
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_resource_address")
@ApiModel(value="TbResourceAddress对象", description="资源地址")
public class TbResourceAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "资源唯一标识")
    private String uuid;

    @ApiModelProperty(value = "渠道")
    private Integer channel;

    @ApiModelProperty(value = "资源地址")
    private String url;

    @ApiModelProperty(value = "资源密码")
    private String pwd;

    @ApiModelProperty(value = "解压密码")
    private String unzip;

    @ApiModelProperty(value = "归属")
    private Integer belong;

    @ApiModelProperty(value = "是否过期")
    private Boolean expire;


}
