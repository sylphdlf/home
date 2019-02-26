package com.dlf.web.listeners;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.dlf.business.manager.user.InitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by Administrator on 2017/5/7.
 */
@Component
public class AfterStart implements CommandLineRunner{
    //引入初始化sql文件
    @Resource
    private InitService initService;

    /**
     * 初始化操作
     * @param strings
     * @throws IOException
     */
    public void run(String... strings) throws IOException {
        //初始化菜单和资源
        //创建一个默认的管理员
        initService.initAdmin();
        initService.initSource();
        System.out.println("AfterStart--------------------");
    }
}
