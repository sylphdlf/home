package utils;

import org.python.util.PythonInterpreter;

/**
 * ��ȡpython�ű�������
 * Created by Administrator on 2017/7/4.
 */
public class PythonDataTransfer {

    private static void getStrData(){
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("../../crawlers/boss.py");
    }

    public static void main(String[] args) {
        getStrData();
    }
}
