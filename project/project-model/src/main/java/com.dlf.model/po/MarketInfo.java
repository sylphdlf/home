package com.dlf.model.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MarketInfo implements Serializable {
    private static final long serialVersionUID = -6672162360341286251L;
    private Long id;

    private String code;

    private String name;

    private String market;

    private BigDecimal price;

    private BigDecimal priceClose;

    private BigDecimal priceOpen;

    private Integer dealCount;

    private BigDecimal dealPrice;

    private Integer outerCount;

    private Integer innerCount;

    private BigDecimal buyingOne;

    private Integer buyingOneCount;

    private BigDecimal buyingTwo;

    private Integer buyingTwoCount;

    private BigDecimal buyingThree;

    private Integer buyingThreeCount;

    private BigDecimal buyingFour;

    private Integer buyingFourCount;

    private BigDecimal buyingFive;

    private Integer buyingFiveCount;

    private BigDecimal sellingOne;

    private Integer sellingOneCount;

    private BigDecimal sellingTwo;

    private Integer sellingTwoCount;

    private BigDecimal sellingThree;

    private Integer sellingThreeCount;

    private BigDecimal sellingFour;

    private Integer sellingFourCount;

    private BigDecimal sellingFive;

    private Integer sellingFiveCount;

    private String recentlyDealInfo;

    private Date refreshTime;

    private BigDecimal riseFall;

    private BigDecimal riseFallRatio;

    private BigDecimal priceHighest;

    private BigDecimal priceLowest;

    private String priceDealInfo;

    private BigDecimal exchangeRatio;

    private BigDecimal earningsRatio;

    private BigDecimal stockAmplitude;

    private BigDecimal liveValue;

    private BigDecimal totalValue;

    private BigDecimal priceBootRatio;

    private BigDecimal limitUp;

    private BigDecimal limitDown;

    private String remarks;

    private Date createTime;

    private Date updateTime;

    private Long createUserId;

    private Long updateUserId;

    private Integer isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market == null ? null : market.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceClose() {
        return priceClose;
    }

    public void setPriceClose(BigDecimal priceClose) {
        this.priceClose = priceClose;
    }

    public BigDecimal getPriceOpen() {
        return priceOpen;
    }

    public void setPriceOpen(BigDecimal priceOpen) {
        this.priceOpen = priceOpen;
    }

    public Integer getDealCount() {
        return dealCount;
    }

    public void setDealCount(Integer dealCount) {
        this.dealCount = dealCount;
    }

    public BigDecimal getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(BigDecimal dealPrice) {
        this.dealPrice = dealPrice;
    }

    public Integer getOuterCount() {
        return outerCount;
    }

    public void setOuterCount(Integer outerCount) {
        this.outerCount = outerCount;
    }

    public Integer getInnerCount() {
        return innerCount;
    }

    public void setInnerCount(Integer innerCount) {
        this.innerCount = innerCount;
    }

    public BigDecimal getBuyingOne() {
        return buyingOne;
    }

    public void setBuyingOne(BigDecimal buyingOne) {
        this.buyingOne = buyingOne;
    }

    public Integer getBuyingOneCount() {
        return buyingOneCount;
    }

    public void setBuyingOneCount(Integer buyingOneCount) {
        this.buyingOneCount = buyingOneCount;
    }

    public BigDecimal getBuyingTwo() {
        return buyingTwo;
    }

    public void setBuyingTwo(BigDecimal buyingTwo) {
        this.buyingTwo = buyingTwo;
    }

    public Integer getBuyingTwoCount() {
        return buyingTwoCount;
    }

    public void setBuyingTwoCount(Integer buyingTwoCount) {
        this.buyingTwoCount = buyingTwoCount;
    }

    public BigDecimal getBuyingThree() {
        return buyingThree;
    }

    public void setBuyingThree(BigDecimal buyingThree) {
        this.buyingThree = buyingThree;
    }

    public Integer getBuyingThreeCount() {
        return buyingThreeCount;
    }

    public void setBuyingThreeCount(Integer buyingThreeCount) {
        this.buyingThreeCount = buyingThreeCount;
    }

    public BigDecimal getBuyingFour() {
        return buyingFour;
    }

    public void setBuyingFour(BigDecimal buyingFour) {
        this.buyingFour = buyingFour;
    }

    public Integer getBuyingFourCount() {
        return buyingFourCount;
    }

    public void setBuyingFourCount(Integer buyingFourCount) {
        this.buyingFourCount = buyingFourCount;
    }

    public BigDecimal getBuyingFive() {
        return buyingFive;
    }

    public void setBuyingFive(BigDecimal buyingFive) {
        this.buyingFive = buyingFive;
    }

    public Integer getBuyingFiveCount() {
        return buyingFiveCount;
    }

    public void setBuyingFiveCount(Integer buyingFiveCount) {
        this.buyingFiveCount = buyingFiveCount;
    }

    public BigDecimal getSellingOne() {
        return sellingOne;
    }

    public void setSellingOne(BigDecimal sellingOne) {
        this.sellingOne = sellingOne;
    }

    public Integer getSellingOneCount() {
        return sellingOneCount;
    }

    public void setSellingOneCount(Integer sellingOneCount) {
        this.sellingOneCount = sellingOneCount;
    }

    public BigDecimal getSellingTwo() {
        return sellingTwo;
    }

    public void setSellingTwo(BigDecimal sellingTwo) {
        this.sellingTwo = sellingTwo;
    }

    public Integer getSellingTwoCount() {
        return sellingTwoCount;
    }

    public void setSellingTwoCount(Integer sellingTwoCount) {
        this.sellingTwoCount = sellingTwoCount;
    }

    public BigDecimal getSellingThree() {
        return sellingThree;
    }

    public void setSellingThree(BigDecimal sellingThree) {
        this.sellingThree = sellingThree;
    }

    public Integer getSellingThreeCount() {
        return sellingThreeCount;
    }

    public void setSellingThreeCount(Integer sellingThreeCount) {
        this.sellingThreeCount = sellingThreeCount;
    }

    public BigDecimal getSellingFour() {
        return sellingFour;
    }

    public void setSellingFour(BigDecimal sellingFour) {
        this.sellingFour = sellingFour;
    }

    public Integer getSellingFourCount() {
        return sellingFourCount;
    }

    public void setSellingFourCount(Integer sellingFourCount) {
        this.sellingFourCount = sellingFourCount;
    }

    public BigDecimal getSellingFive() {
        return sellingFive;
    }

    public void setSellingFive(BigDecimal sellingFive) {
        this.sellingFive = sellingFive;
    }

    public Integer getSellingFiveCount() {
        return sellingFiveCount;
    }

    public void setSellingFiveCount(Integer sellingFiveCount) {
        this.sellingFiveCount = sellingFiveCount;
    }

    public String getRecentlyDealInfo() {
        return recentlyDealInfo;
    }

    public void setRecentlyDealInfo(String recentlyDealInfo) {
        this.recentlyDealInfo = recentlyDealInfo == null ? null : recentlyDealInfo.trim();
    }

    public Date getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Date refreshTime) {
        this.refreshTime = refreshTime;
    }

    public BigDecimal getRiseFall() {
        return riseFall;
    }

    public void setRiseFall(BigDecimal riseFall) {
        this.riseFall = riseFall;
    }

    public BigDecimal getRiseFallRatio() {
        return riseFallRatio;
    }

    public void setRiseFallRatio(BigDecimal riseFallRatio) {
        this.riseFallRatio = riseFallRatio;
    }

    public BigDecimal getPriceHighest() {
        return priceHighest;
    }

    public void setPriceHighest(BigDecimal priceHighest) {
        this.priceHighest = priceHighest;
    }

    public BigDecimal getPriceLowest() {
        return priceLowest;
    }

    public void setPriceLowest(BigDecimal priceLowest) {
        this.priceLowest = priceLowest;
    }

    public String getPriceDealInfo() {
        return priceDealInfo;
    }

    public void setPriceDealInfo(String priceDealInfo) {
        this.priceDealInfo = priceDealInfo == null ? null : priceDealInfo.trim();
    }

    public BigDecimal getExchangeRatio() {
        return exchangeRatio;
    }

    public void setExchangeRatio(BigDecimal exchangeRatio) {
        this.exchangeRatio = exchangeRatio;
    }

    public BigDecimal getEarningsRatio() {
        return earningsRatio;
    }

    public void setEarningsRatio(BigDecimal earningsRatio) {
        this.earningsRatio = earningsRatio;
    }

    public BigDecimal getStockAmplitude() {
        return stockAmplitude;
    }

    public void setStockAmplitude(BigDecimal stockAmplitude) {
        this.stockAmplitude = stockAmplitude;
    }

    public BigDecimal getLiveValue() {
        return liveValue;
    }

    public void setLiveValue(BigDecimal liveValue) {
        this.liveValue = liveValue;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getPriceBootRatio() {
        return priceBootRatio;
    }

    public void setPriceBootRatio(BigDecimal priceBootRatio) {
        this.priceBootRatio = priceBootRatio;
    }

    public BigDecimal getLimitUp() {
        return limitUp;
    }

    public void setLimitUp(BigDecimal limitUp) {
        this.limitUp = limitUp;
    }

    public BigDecimal getLimitDown() {
        return limitDown;
    }

    public void setLimitDown(BigDecimal limitDown) {
        this.limitDown = limitDown;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}