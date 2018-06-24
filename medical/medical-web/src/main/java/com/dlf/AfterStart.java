package com.dlf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/5/7.
 */
@Component
public class AfterStart implements CommandLineRunner{

    public void run(String... strings) throws Exception {
        System.out.println("启动成功--------------");
    }
}
