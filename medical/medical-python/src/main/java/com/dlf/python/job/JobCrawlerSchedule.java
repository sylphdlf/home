package com.dlf.python.job;

import com.dlf.business.exception.MyException;
import com.dlf.business.manager.python.IPythonDataTransferService;
import com.dlf.python.utils.PythonDataTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/7/5.
 */
@Component
public class JobCrawlerSchedule {


    @Value("${job.crawler.location}")
    private static String jobCrawlerLocation;

    @Value("${job.weather.location}")
    private static String jobWeatherLocation;

    @Autowired
    IPythonDataTransferService pythonDataTransferService;




//    @Scheduled(cron = "0/20 * * * * ?")
//    public void getJobKeyword(){
//        System.out.println("定时任务执行");
//        String jobStrData = getJobStrData(jobCrawlerLocation);
//        pythonDataTransferService.transferDataFromWordSplit(jobStrData);
//    }
    @Scheduled(cron = "0/10 * * * * ?")
    public void getWeatherData(){
        try {
//            String jobStrData = PythonDataTransfer.getStrData(jobWeatherLocation);
            String jobStrData = PythonDataTransfer.getStrData("D:\\develop\\workspace-home\\medical\\medical-python\\src\\main\\python\\crawlers\\weather_city.py");
            pythonDataTransferService.transferDataFromWeather(jobStrData);
        }catch (Exception e){
            e.getMessage();
        }
    }
}
