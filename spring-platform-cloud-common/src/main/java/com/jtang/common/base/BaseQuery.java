package com.jtang.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * description 基础查询类
 * @date 2020/3/2 11:10
 * @author LinJinTang
 */
@Getter
@Setter
@ToString
public class BaseQuery implements Serializable {

    @ApiModelProperty(name = "pageIndex" , value = "页码")
    private Integer pageIndex;

    @ApiModelProperty(name = "pageSize", value = "页码")
    private Integer pageSize;
}
