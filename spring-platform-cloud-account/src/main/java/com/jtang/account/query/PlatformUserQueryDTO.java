package com.jtang.account.query;
import com.jtang.common.base.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 用户 查询类
* @author lin512100
* @since 2020-06-30
* @version v1.0
*/
@Getter
@Setter
@ToString
public class PlatformUserQueryDTO extends BaseQuery {

    @ApiModelProperty(name = "username" , value = "用户名", example = "username")
    private String username;
}
