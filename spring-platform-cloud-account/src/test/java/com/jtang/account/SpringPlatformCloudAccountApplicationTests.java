package com.jtang.account;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.jtang.generator.CodeGenerator;
import com.jtang.generator.GeneratorParamDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringPlatformCloudAccountApplicationTests {

    @Test
    void contextLoads() {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://129.204.1.173:3306/platform-account?useUnicode=true&useSSL=false&characterEncoding=utf8");
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
        generatorParamDTO.setModuleName("bussiness");
        CodeGenerator.genertor(generatorParamDTO , dsc);

    }

}
