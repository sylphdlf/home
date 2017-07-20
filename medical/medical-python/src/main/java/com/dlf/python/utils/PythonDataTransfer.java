package com.dlf.python.utils;

import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 获取python脚本的数据
 * Created by Administrator on 2017/7/4.
 */
public class PythonDataTransfer {

    @Value("${job.crawler.location}")
    private static String jobCrawlerLocation;

    public static String getJobStrData(){
        String returnLine = "";
        try {
            File file = new File("");
            Process exec = Runtime.getRuntime().exec("python " + jobCrawlerLocation);
            BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            BufferedReader errorBr = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
            String line;
            while((line = br.readLine()) != null){
                returnLine += line;
            }
            String errorLine;
            while ((errorLine = errorBr.readLine()) != null){
                System.out.println(errorLine);
            }
            br.close();
            exec.waitFor();
            System.out.println("end");
        }catch (Exception e){
            System.out.println("~");
        }
        return returnLine;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        getJobStrData();
    }
}
