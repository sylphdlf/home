package com.dlf.model.po;

import java.math.BigDecimal;
import java.util.Date;

public class Market {
    private Long id;

    private String code;

    private String name;

    private String market;

    private Integer type;

    private BigDecimal price;

    private BigDecimal riseFall;

    private BigDecimal riseFallRatio;

    private String riseFallHistory;

    private Integer days;

    private Integer watchingStatus;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getRiseFallHistory() {
        return riseFallHistory;
    }

    public void setRiseFallHistory(String riseFallHistory) {
        this.riseFallHistory = riseFallHistory;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getWatchingStatus() {
        return watchingStatus;
    }

    public void setWatchingStatus(Integer watchingStatus) {
        this.watchingStatus = watchingStatus;
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