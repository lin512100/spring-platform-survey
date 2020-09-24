import com.jtang.NacosApplication;
import com.jtang.system.service.ITbSystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NacosApplication.class)
public class XADataTest {

    @Autowired
    private ITbSystemService systemService;


    @Test
    public void errorTest(){
        systemService.all();

    }
}
