package com.dlf.common.utils;

import java.util.UUID;

/**
 * code生成工具
 */
public class CodeGenerateUtils {

    public static final String SOURCES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    /**
     * 获取验证码
     * @return
     */
    public static Integer getCheckCode6(){
        return (int)((Math.random()*9+1)*100000);

    }

    /**
     * 获取4位随机数
     * @return
     */
    public static Integer getCode4(){
        return (int)((Math.random()*9+1)*1000);
    }

    /**
     * user_ticket生成工具
     * @return
     */
    public static String userTicketGenerate(){
        return "UT" + UUID.randomUUID().toString().replace("-", "") + DateTimeUtils.getCurrentTimeStr();
    }

    /**
     * 根据前缀生成单号
     * @param prefix
     * @return
     */
    public static String codeGenerateByPrefix(String prefix){
        return prefix + DateTimeUtils.getCurrentTimeStr() + (int)((Math.random()*9+1)*1000);
    }
}
