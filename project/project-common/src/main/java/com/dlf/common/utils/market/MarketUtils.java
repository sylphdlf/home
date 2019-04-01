package com.dlf.common.utils.market;

import com.dlf.common.utils.HttpUtils;

public class MarketUtils {

    public static String[] queryMarketInfo(String url){
        String returnStr = HttpUtils.httpGet(url);
        String thisStr = returnStr.split("=")[1];
        return thisStr.split("~");
    }
}
