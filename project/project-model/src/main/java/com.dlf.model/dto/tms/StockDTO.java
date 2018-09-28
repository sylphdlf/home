package com.dlf.model.dto.tms;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class StockDTO {
    private Long id;
    private Long productId;
    private Integer count;
    private Integer countFrozen;
    private Integer volume;
    private Integer weight;
    private BigDecimal price;
    private String remarks;
    private Date createTime;
    private Date updateTime;
    //商品
    private String productName;
    private String productType;
    private String productPackaging;
    private Integer productVolume;
    private Integer productWeight;
    private BigDecimal productPrice;
    private Integer productLength;
    private Integer productWidth;
    private Integer productHeight;
    private String productRemarks;
}
