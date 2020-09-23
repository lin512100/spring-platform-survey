package com.jtang.resource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 
* @author jtang
* @date 2020-09-13
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_resource_visit_log")
@ApiModel(value="TbResourceVisitLog对象", description="")
public class TbResourceVisitLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "资源序列化")
    private String uuid;

    @ApiModelProperty(value = "渠道")
    private Integer channel;

    @ApiModelProperty(value = "链接来源")
    private String source;

    @ApiModelProperty(value = "访问时间")
    private LocalDateTime time;

    @ApiModelProperty(value = "IP来源")
    private String ip;


}
