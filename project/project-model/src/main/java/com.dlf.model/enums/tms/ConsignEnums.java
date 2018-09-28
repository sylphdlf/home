package com.dlf.model.enums.tms;

import org.springframework.util.CollectionUtils;

import java.util.*;

public enum ConsignEnums {

    CONTACTS_TYPE_1(1, "发货人"),
    CONTACTS_TYPE_2(2, "收货人"),
    //回单付，现付，提付，月结，现付未付，货款扣，免费，货到付款（提付），两笔付（不限制，合计不变），欠付，货到打卡
    PAY_TYPE_1(1, "回单付"),
    PAY_TYPE_2(2, "现付"),
    PAY_TYPE_3(3, "提付"),
    PAY_TYPE_4(4, "月结"),
    PAY_TYPE_5(5, "现付未付"),
    PAY_TYPE_6(6, "贷款扣"),
    PAY_TYPE_7(6, "免费"),
    PAY_TYPE_8(6, "货到付款（提付）"),
    PAY_TYPE_9(6, "两笔付"),
    PAY_TYPE_10(6, "欠付"),
    PAY_TYPE_11(6, "货到打卡"),
    CONSIGN_STATUS_0(0, "草稿"),
    CONSIGN_STATUS_1(1, "完成"),
    CONSIGN_STATUS_2(2, "提交"),
    ;
    private Integer code;
    private String msg;
    private static List<Map<Integer, String>> payTypeList = new ArrayList<Map<Integer, String>>();

    ConsignEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 获取付款方式
     * @return
     */
//    public Map<Integer, String> getPayTypes(){
//        if(CollectionUtils.isEmpty(payTypeList)){
//            payTypeList.add(new HashMap<Integer, String>(){PAY_TYPE_1.getCode(), PAY_TYPE_1.getMsg()});
//            payTypeMap.put(PAY_TYPE_1.getCode(), PAY_TYPE_1.getMsg());
//            payTypeMap.put(PAY_TYPE_2.getCode(), PAY_TYPE_2.getMsg());
//            payTypeMap.put(PAY_TYPE_3.getCode(), PAY_TYPE_3.getMsg());
//            payTypeMap.put(PAY_TYPE_4.getCode(), PAY_TYPE_4.getMsg());
//            payTypeMap.put(PAY_TYPE_5.getCode(), PAY_TYPE_5.getMsg());
//            payTypeMap.put(PAY_TYPE_6.getCode(), PAY_TYPE_6.getMsg());
//            payTypeMap.put(PAY_TYPE_7.getCode(), PAY_TYPE_7.getMsg());
//            payTypeMap.put(PAY_TYPE_8.getCode(), PAY_TYPE_8.getMsg());
//            payTypeMap.put(PAY_TYPE_9.getCode(), PAY_TYPE_9.getMsg());
//            payTypeMap.put(PAY_TYPE_10.getCode(), PAY_TYPE_10.getMsg());
//            payTypeMap.put(PAY_TYPE_11.getCode(), PAY_TYPE_11.getMsg());
//        }
//        return payTypeMap;
//    }
}
