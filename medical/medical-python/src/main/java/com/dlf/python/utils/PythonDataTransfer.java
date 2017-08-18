package com.dlf.python.utils;

import com.dlf.business.exception.MyException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 获取python脚本的数据
 * Created by Administrator on 2017/7/4.
 */
public class PythonDataTransfer {

    private static Log log = LogFactory.getLog(PythonDataTransfer.class);

    public static String getStrData(String pythonPath) throws MyException{
        String returnLine = "";
        try {
            if(null == pythonPath || "".equals(pythonPath)){
                return "";
            }
            Process exec = Runtime.getRuntime().exec("python " + pythonPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream(), "UTF-8"));
            BufferedReader errorBr = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
            String line;
            while((line = br.readLine()) != null){
                line = new String(line.getBytes(),"UTF-8");
                returnLine += line;
            }
            String errorLine;
            while ((errorLine = errorBr.readLine()) != null){
                log.error(errorLine);
            }
            br.close();
            exec.waitFor();
        }catch (Exception e){
            throw new MyException(e.getMessage());
        }
        return returnLine;
    }
}
