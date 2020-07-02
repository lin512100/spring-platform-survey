package com.jtang.oauth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 客户端信息
* @author jtang
* @date 2020-07-02
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oauth_client_details")
@ApiModel(value="ClientDetails对象", description="客户端信息")
public class ClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户端ID")
    private String clientId;

    @ApiModelProperty(value = "资源ID")
    private String resourceIds;

    @ApiModelProperty(value = "客户端密钥")
    private String clientSecret;

    @ApiModelProperty(value = "作用范围")
    private String scope;

    @ApiModelProperty(value = "授予类型")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "重定向地址")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "权限值")
    private String authorities;

    @ApiModelProperty(value = "有效时间值")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "刷新的有效时间")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "预留字段")
    private String additionalInformation;

    @ApiModelProperty(value = "自动Approval操作")
    private String autoapprove;


}
