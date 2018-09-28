package com.dlf.model.dto.tms;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class ConsignReqDTO {

    private Long id;
    private String departure;
    private String destination;
    private Long consignorId;
    private String consignorName;
    private String consignorTel;
    private String consignorMobile;
    private String consignorAddr;
    private Long consigneeId;
    private String consigneeName;
    private String consigneeTel;
    private String consigneeMobile;
    private String consigneeAddr;
    private Long vehicleId;
    private String loadingPlace;
    private Date vehicleLeaveTime;
    private Date cargoLeaveTime;
    private Date expectedArriveTime;
    private Date cargoPackageTime;
    private Integer payType;
    private Integer transportType;
    private Integer receiptType;
    private Integer deliveryType;
    private BigDecimal basicPrice;
    private BigDecimal vehiclePrice;
    private BigDecimal payNow;
    private BigDecimal payDelivery;
    private BigDecimal payReceipt;
    private BigDecimal payMonthly;
    private BigDecimal payAccount;
    private BigDecimal payTotal;
    private Integer status;
    private String remarks;
    //商品信息
    private Long productId;
    private String productName;
    private String productType;
    private Integer productCount;
    private String productPackaging;
    private Integer productVolume;
    private Integer productWeight;
    private BigDecimal productPrice;
    private Integer productLength;
    private Integer productWidth;
    private Integer productHeight;
    private String productRemarks;
    //库存信息
    private Integer stockCount;
    private Integer stockVolume;
    private Integer stockWeight;
    private BigDecimal stockPrice;
    private String stockRemarks;
}
