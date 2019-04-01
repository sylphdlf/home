package com.dlf.model.enums.stock;

import com.dlf.common.utils.DateTimeUtils;
import com.dlf.model.po.MarketInfo;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public enum GtimgEnums {
    MARKET_URL(1, "http://qt.gtimg.cn/q=")
    ,
    INDEX_1(1, "股票名称"),INDEX_2(2, "股票代码"),INDEX_3(3, "当前价格"),INDEX_4(4, "昨收"),INDEX_5(5, "今开"),INDEX_6(6, "成交量（手）"),INDEX_7(7, "外盘"),
    INDEX_8(8, "内盘"),INDEX_9(9, "买一"), INDEX_10(10, "买一量（手）"),INDEX_11(11, "买二"),INDEX_12(12, "买二量（手）"),INDEX_13(13, "买三"),INDEX_14(14, "买三量（手）"),
    INDEX_15(15, "买四"),INDEX_16(16, "买四量（手）"),INDEX_17(17, "买五"),INDEX_18(18, "买五量（手）"),INDEX_19(19, "卖一"),INDEX_20(20, "卖一量（手）"),INDEX_21(21, "卖二"),
    INDEX_22(22, "卖二量"),INDEX_23(23, "卖三"),INDEX_24(24, "卖三量"),INDEX_25(25, "卖四"),INDEX_26(26, "卖四量"),INDEX_27(27, "卖五"),INDEX_28(28, "卖五量"),
    INDEX_29(29, "最近逐笔成交"),INDEX_30(30, "时间"),INDEX_31(31, "涨跌"),INDEX_32(32, "涨跌%"),INDEX_33(33, "最高"),INDEX_34(34, "最低"),INDEX_35(35, "价格/成交量（手）/成交额"),
    INDEX_36(36, "成交量（手）"),INDEX_37(37, "成交额（万）"),INDEX_38(38, "换手率"),INDEX_39(39, "市盈率"),INDEX_40(40, ""),INDEX_41(41, "最高"),INDEX_42(42, "最低"),
    INDEX_43(43, "振幅"),INDEX_44(44, "流通市值"),INDEX_45(45, "总市值"),INDEX_46(46, "市净率"),INDEX_47(47, "涨停价"),INDEX_48(48, "跌停价")
    ;
    private int index;
    private String name;

    private static Map<Integer, String> indexNameMap;

    GtimgEnums(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public static void setParams(MarketInfo marketInfo, String[] strList) {
        marketInfo.setName(strList[INDEX_1.index]);
        marketInfo.setCode(strList[INDEX_2.index]);
        marketInfo.setPrice(new BigDecimal(strList[INDEX_3.index]));
        marketInfo.setPriceClose(new BigDecimal(strList[INDEX_4.index]));
        marketInfo.setPriceOpen(new BigDecimal(strList[INDEX_5.index]));
        marketInfo.setDealCount(Integer.valueOf(strList[INDEX_6.index]));
        marketInfo.setOuterCount(Integer.valueOf(strList[INDEX_7.index]));
        marketInfo.setInnerCount(Integer.valueOf(strList[INDEX_8.index]));
        marketInfo.setBuyingOne(new BigDecimal(strList[INDEX_9.index]));
        marketInfo.setBuyingOneCount(Integer.valueOf(strList[INDEX_10.index]));
        marketInfo.setBuyingTwo(new BigDecimal(strList[INDEX_11.index]));
        marketInfo.setBuyingTwoCount(Integer.valueOf(strList[INDEX_12.index]));
        marketInfo.setBuyingThree(new BigDecimal(strList[INDEX_13.index]));
        marketInfo.setBuyingThreeCount(Integer.valueOf(strList[INDEX_14.index]));
        marketInfo.setBuyingFour(new BigDecimal(strList[INDEX_15.index]));
        marketInfo.setBuyingFourCount(Integer.valueOf(strList[INDEX_16.index]));
        marketInfo.setBuyingFive(new BigDecimal(strList[INDEX_17.index]));
        marketInfo.setBuyingFiveCount(Integer.valueOf(strList[INDEX_18.index]));
        marketInfo.setSellingOne(new BigDecimal(strList[INDEX_19.index]));
        marketInfo.setSellingOneCount(Integer.valueOf(strList[INDEX_20.index]));
        marketInfo.setSellingTwo(new BigDecimal(strList[INDEX_21.index]));
        marketInfo.setSellingTwoCount(Integer.valueOf(strList[INDEX_22.index]));
        marketInfo.setSellingThree(new BigDecimal(strList[INDEX_23.index]));
        marketInfo.setSellingThreeCount(Integer.valueOf(strList[INDEX_24.index]));
        marketInfo.setSellingFour(new BigDecimal(strList[INDEX_25.index]));
        marketInfo.setSellingFourCount(Integer.valueOf(strList[INDEX_26.index]));
        marketInfo.setSellingFive(new BigDecimal(strList[INDEX_27.index]));
        marketInfo.setSellingFiveCount(Integer.valueOf(strList[INDEX_28.index]));
        marketInfo.setRecentlyDealInfo(strList[INDEX_29.index]);
        marketInfo.setRefreshTime(DateTimeUtils.transferStrToDate(strList[INDEX_30.index], DateTimeUtils.DEFAULT_PATTERN));
        marketInfo.setRiseFall(new BigDecimal(strList[INDEX_31.index]));
        marketInfo.setRiseFallRatio(new BigDecimal(strList[INDEX_32.index]));
        marketInfo.setPriceHighest(new BigDecimal(strList[INDEX_33.index]));
        marketInfo.setPriceLowest(new BigDecimal(strList[INDEX_34.index]));
        marketInfo.setPriceDealInfo(strList[INDEX_35.index]);
        marketInfo.setDealPrice(exchangeNull(strList[INDEX_37.index]));
        marketInfo.setExchangeRatio(exchangeNull(strList[INDEX_38.index]));
        marketInfo.setEarningsRatio(exchangeNull(strList[INDEX_39.index]));
        marketInfo.setStockAmplitude(exchangeNull(strList[INDEX_43.index]));
        marketInfo.setLiveValue(exchangeNull(strList[INDEX_44.index]));
        marketInfo.setTotalValue(exchangeNull(strList[INDEX_45.index]));
        marketInfo.setPriceBootRatio(exchangeNull(strList[INDEX_46.index]));
        marketInfo.setLimitUp(exchangeNull(strList[INDEX_47.index]));
        marketInfo.setLimitDown(exchangeNull(strList[INDEX_48.index]));
    }

    public synchronized void setIndexNameMap(){
        if(CollectionUtils.isEmpty(indexNameMap)){
            indexNameMap = new HashMap<Integer, String>();
            for (GtimgEnums gtimgEnums : GtimgEnums.values()) {
                indexNameMap.put(gtimgEnums.index, gtimgEnums.name);
            }
        }
    }
    private static BigDecimal exchangeNull(String inputStr){
        return new BigDecimal(inputStr.equals("")?"0":inputStr);
    }
}
