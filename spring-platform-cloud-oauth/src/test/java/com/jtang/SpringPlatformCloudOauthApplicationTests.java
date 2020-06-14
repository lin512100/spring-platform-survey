package com.jtang;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.jtang.generator.CodeGenerator;
import com.jtang.generator.GeneratorParamDTO;
import com.jtang.oauth.entity.TbPersistentLogin;
import com.jtang.oauth.service.ITbPersistentLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SpringPlatformCloudOauthApplicationTests {

    @Autowired
    private ITbPersistentLoginService tbPersistentLoginService;

    @Test
    public void tbPersistentLoginService(){
        QueryWrapper<TbPersistentLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TbPersistentLogin::getSeries, "1");
        tbPersistentLoginService.getOne(queryWrapper);
    }

    @Test
    void contextLoads() {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/platform-oauth?useUnicode=true&serverTimezone=UTC&useSSL=false");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");

        GeneratorParamDTO generatorParamDTO = new GeneratorParamDTO();
        // 设置生成地址
        generatorParamDTO.setProjectPath(System.getProperty("user.dir"));

        // 设置父类包名
        generatorParamDTO.setParent("com.jtang");

        // 设置作者
        generatorParamDTO.setAuthor("jtang");
        generatorParamDTO.setModuleName("oauth");
        CodeGenerator.genertor(generatorParamDTO , dsc);

    }

}
