package com.dlf.python.job;

import com.dlf.business.manager.python.IPythonDataTransferService;
import com.dlf.python.utils.PythonDataTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.dlf.python.utils.PythonDataTransfer.getJobStrData;

/**
 * Created by Administrator on 2017/7/5.
 */
@Component
public class JobCrawlerSchedule {

    @Autowired
    IPythonDataTransferService pythonDataTransferService;

    @Scheduled(cron = "0/20 * * * * ?")
    public void getJobKeyword(){
        System.out.println("定时任务执行");
        String jobStrData = getJobStrData();
        pythonDataTransferService.transferDataFromWordSplit(jobStrData);
    }
}
