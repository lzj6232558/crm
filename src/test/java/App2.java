import cn.wolfcode.crm.service.IcheckproductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class App2 {

    @Autowired
    private IcheckproductService checkproductService;
    @Test
    public void testAAA() throws Exception {
    }
}
