package com.jtang.web.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * description 数据列表数据结构
 * @date 2020/3/2 11:37
 * @author LinJinTang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T> implements Serializable {

    /**
     * 总记录数
     */
    private long total;

    /**
     * 单页记录集合
     */
    private List<T> rows;

}
