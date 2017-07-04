package com.dlf.python.job;

import com.dlf.business.manager.python.IPythonDataTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/7/5.
 */
@Component
public class JobCrawlerSchedule {

    @Autowired
    IPythonDataTransferService pythonDataTransferService;

    @Scheduled(cron = "20 * * * * ?")
    public void getJobKeyword(){
        System.out.println("定时任务执行");
    }
}
