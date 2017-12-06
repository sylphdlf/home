import com.dlf.Application;
import com.dlf.business.mq.producer.TestProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * description
 * Date: 2017/12/5
 *
 * @author dailf@zendaimoney.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class RabbitMQTest {
    @Autowired
    TestProducer testProducer;
    @Test
    public void send(){
        for(int i=0;i<100;i++){
            testProducer.send();
        }
    }
}
