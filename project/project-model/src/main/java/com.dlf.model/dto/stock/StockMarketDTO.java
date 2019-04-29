package com.dlf.model.dto.stock;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class StockMarketDTO {

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
    private String highLow;
    private BigDecimal earningsRatio;
    private String earningsRatioHistory;
    private BigDecimal exchangeRatio;
    private String exchangeRatioHistory;
    private Integer watchingStatus;
    private Date createTime;
    private Date updateTime;
    private Integer isDeleted;
}
