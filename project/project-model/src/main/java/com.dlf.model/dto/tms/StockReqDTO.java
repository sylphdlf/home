package com.dlf.model.dto.tms;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class StockReqDTO {
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
    private Long createUserId;
    private Long updateUserId;
    private Integer isDeleted;

    public void setStockFromConsign(ConsignReqDTO reqDTO){
        this.productId = reqDTO.getProductId();
        this.count = reqDTO.getStockCount();
        this.volume = reqDTO.getStockVolume();
        this.weight = reqDTO.getStockWeight();
        this.price = reqDTO.getStockPrice();
    }
}
