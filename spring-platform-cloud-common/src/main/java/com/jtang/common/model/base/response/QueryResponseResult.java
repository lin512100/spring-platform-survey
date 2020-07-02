package com.jtang.common.model.base.response;

import com.jtang.common.model.base.response.QueryResult;
import com.jtang.common.model.base.response.ResponseResult;
import com.jtang.common.model.base.response.ResultCode;
import lombok.*;

/**
 * @date 2020/7/2 20:56
 * @author LinJinTang
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class QueryResponseResult<T> extends ResponseResult {

    QueryResult<T> queryResult;

    public QueryResponseResult(ResultCode resultCode, QueryResult queryResult){
        super(resultCode);
       this.queryResult = queryResult;
    }

}
