package com.jtang.web.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * description 自动代码生成器
 * @date 2020/2/28 16:12
 * @author LinJinTang
 */
public class CodeGenerator {

    private static final String QUERY_TEMPLATE_PATH = "/templates/query.java.ftl";

    private static final String ENTITY_TEMPLATE_PATH = "/templates/entity.java.ftl";

    private static final String MAPPER_TEMPLATE_PATH = "/templates/mapper.java.ftl";

    private static final String MAPPER_XML_TEMPLATE_PATH = "/templates/mapper.xml.ftl";

    private static final String SERVICE_TEMPLATE_PATH = "/templates/service.java.ftl";

    private static final String SERVICE_IMPL_TEMPLATE_PATH = "/templates/serviceImpl.java.ftl";

    private static final String CONTROLLER_TEMPLATE_PATH = "/templates/controller.java.ftl";

    public static void main(String[] args) {

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/web-item?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");

        GeneratorParamDTO generatorParamDTO = new GeneratorParamDTO();
        // 设置生成地址
        generatorParamDTO.setProjectPath(System.getProperty("user.dir"));

        // 设置父类包名
        generatorParamDTO.setParent("com.jtang");

        // 设置作者
        generatorParamDTO.setAuthor("jtang");

        genertor(generatorParamDTO , dsc);
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (!StringUtils.isBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void genertor(GeneratorParamDTO generatorParamDTO , DataSourceConfig dataSourceConfig) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(generatorParamDTO.getProjectPath() + "/src/main/java");
        gc.setAuthor(generatorParamDTO.getAuthor());
        gc.setOpen(false);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        mpg.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // 模块名
        pc.setModuleName(generatorParamDTO.getModuleName());
        pc.setParent(generatorParamDTO.getParent());
        mpg.setPackageInfo(pc);

        // 自定义配置
        generatorParamDTO.setModuleName(pc.getModuleName());
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        //Mapper xml 文件
        focList.add(new FileOutConfig(MAPPER_XML_TEMPLATE_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {

                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return generatorParamDTO.getProjectPath() + "/src/main/resources/mapper/" + generatorParamDTO.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        // Query查询文件
        focList.add(new FileOutConfig(QUERY_TEMPLATE_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return generatorParamDTO.getProjectPath() + "/src/main/java/"+ generatorParamDTO.getParent().replace(".", "/")
                        + "/" +generatorParamDTO.getModuleName() + "/query/"
                        + "/" + tableInfo.getEntityName()+ "QueryDTO" + StringPool.DOT_JAVA;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        strategy.setInclude(generatorParamDTO.getIncludeTableList());
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}