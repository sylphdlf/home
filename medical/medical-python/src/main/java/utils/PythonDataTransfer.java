package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 获取python脚本的数据
 * Created by Administrator on 2017/7/4.
 */
public class PythonDataTransfer {

    private static void getStrData() throws IOException, InterruptedException {
        File file = new File("");
        Process exec = Runtime.getRuntime().exec("python D:\\develop\\workspace-home\\medical\\medical-python\\src\\main\\python\\crawlers\\boss.py");
        BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        BufferedReader errorBr = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
        String line;
        while((line = br.readLine()) != null){
            System.out.println(line);
        }
        String errorLine;
        while ((errorLine = errorBr.readLine()) != null){
            System.out.println(errorLine);
        }
        br.close();
        exec.waitFor();
        System.out.println("end");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        getStrData();
    }
}
