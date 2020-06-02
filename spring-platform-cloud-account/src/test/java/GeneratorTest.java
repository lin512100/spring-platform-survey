import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.jtang.generator.CodeGenerator;
import com.jtang.generator.GeneratorParamDTO;
import org.junit.Test;


public class GeneratorTest {

    @Test
    public void main(){
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
