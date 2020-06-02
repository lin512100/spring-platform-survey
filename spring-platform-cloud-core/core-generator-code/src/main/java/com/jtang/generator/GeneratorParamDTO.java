package com.jtang.generator;


import lombok.*;

import java.util.List;

/**
 * description 基础信息封装
 * @date 2020/3/2 14:19
 * @author LinJinTang
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GeneratorParamDTO {

    /**
     * 生成路径
     */
    private String projectPath;

    /**
     * 父包名
     */
    private String parent;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 自定义Entity基类全类名
     */
    private String superEntityClass;

    /**
     * service接口自定义基类
     */
    private String superServiceClass;

    /**
     * serviceImpl自定义基类
     */
    private String superServiceImplClass;

    /**
     * 包涵的表名
     */
    private String[] includeTableList;

    /**
     * 作者
     */
    private String author;
}
