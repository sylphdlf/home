package com.dlf.python.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 获取python脚本的数据
 * Created by Administrator on 2017/7/4.
 */
public class PythonDataTransfer {

    public static String getJobStrData(String pythonPath){
        String returnLine = "";
        try {
            if(null == pythonPath || "".equals(pythonPath)){
                return "";
            }
            Process exec = Runtime.getRuntime().exec("python " + pythonPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            BufferedReader errorBr = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
            String line;
            while((line = br.readLine()) != null){
                line = new String(line.getBytes(),"UTF-8");
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
}
