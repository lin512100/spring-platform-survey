package com.jtang.account;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.jtang.web.generator.CodeGenerator;
import com.jtang.web.generator.GeneratorParamDTO;
import org.junit.jupiter.api.Test;

class AccountApplicationTests {

//    @Test
    void contextLoads() {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://129.204.1.173:3306/platform-oauth?useUnicode=true&serverTimezone=UTC&useSSL=false");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Jtang!14010025");

        GeneratorParamDTO generatorParamDTO = new GeneratorParamDTO();
        // 设置生成地址
        generatorParamDTO.setProjectPath(System.getProperty("user.dir"));

        // 设置父类包名
        generatorParamDTO.setParent("com.jtang");

        // 设置作者
        generatorParamDTO.setAuthor("jtang");
        generatorParamDTO.setModuleName("account");
        String[] includeTable = new String[]{"platform_role_menu"};
        generatorParamDTO.setIncludeTableList(includeTable);
        CodeGenerator.genertor(generatorParamDTO , dsc);
    }

}
