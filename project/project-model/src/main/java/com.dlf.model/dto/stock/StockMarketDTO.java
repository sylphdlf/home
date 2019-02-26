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
    private BigDecimal buyingPrice;
    private Integer buyingCount;
    private BigDecimal buyingTotal;
    private Integer watchingStatus;
    private Date createTime;
    //detail
    private BigDecimal price;
}
