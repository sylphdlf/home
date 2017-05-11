package init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/5/7.
 */
@Component
public class AfterStart implements CommandLineRunner{

    public void run(String... strings) throws Exception {
        System.out.println("commandLineRunner-------------------");
    }
}
