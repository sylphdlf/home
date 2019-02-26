package com.dlf.model.dto.stock;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class MarketInfoDTO {

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
}
