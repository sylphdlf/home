package com.dlf.common.utils.message;

import com.dlf.common.utils.HttpUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 发送短信工具类
 */
public class SmsSendUtils {

    private static final String SMS_SEND_URL = "http://sapi.253.com/msg/HttpBatchSendSM";
//    private static final String ACCOUNT = "N3474560";
    private static final String ACCOUNT = "vip-kjkc";
//    private static final String PASSWORD = "jRb0pGmNXBdc3e";
    private static final String PASSWORD = "Kjkc8888";

    public static void send(String target, String content){

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("account", ACCOUNT);
//        jsonObject.put("password", PASSWORD);
//        jsonObject.put("msg","您好，您的验证码是" + content);
//        jsonObject.put("phone",target);
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("account", ACCOUNT));
        formParams.add(new BasicNameValuePair("pswd", PASSWORD));
        formParams.add(new BasicNameValuePair("mobile", target));
        formParams.add(new BasicNameValuePair("msg", "您好，您的验证码是" + content));
        formParams.add(new BasicNameValuePair("needstatus", "false"));
        HttpUtils.httpPost(SMS_SEND_URL, formParams);
    }
    /**
     * 验证手机号码，11位数字，1开通，第二位数必须是3456789这些数字之一 *
     * @param mobileNumber
     * @return
     */
    public static boolean isPhone(String mobileNumber) {
        try {
            // Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Pattern regex = Pattern.compile("^1[3456789]\\d{9}$");
            Matcher matcher = regex.matcher(mobileNumber);
            return matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        send("13761053109", "1234");
    }
}
