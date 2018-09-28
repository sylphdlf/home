package com.dlf.model.dto.tms;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class ConsignDTO {

    private Long consignId;

    private String departure;

    private String destination;

    private String consignCode;

    private Long consignorId;

    private Long consigneeId;

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

    private Integer basicPrice;

    private Integer vehiclePrice;

    private Integer payNow;

    private Integer payDelivery;

    private Integer payReceipt;

    private Integer payMonthly;

    private Integer payAccount;

    private Integer payTotal;

    private Integer status;

    private String remarks;

    private Date createTime;

    //联系人和车辆数据

    private String consignorName;

    private String consignorTel;

    private String consignorMobile;

    private String consignorAddr;

    private String consigneeName;

    private String consigneeTel;

    private String consigneeMobile;

    private String consigneeAddr;

    private String driverName;

    private String vehicleNumber;

    private String driverMobile;

    //商品数据
    private Long productId;

    private String productName;

    private String productType;

    private String productPackaging;

    private Integer productVolume;

    private Integer productWeight;

    private BigDecimal productPrice;

    private Integer productLength;

    private Integer productWidth;

    private Integer productHeight;

    //库存数据

    private Integer stockCount;
    private Integer stockVolume;
    private Integer stockWeight;
    private BigDecimal stockPrice;

    public void setConsignor(ContactsResDTO resDTO){
        this.consignorName = resDTO.getName();
        this.consignorTel = resDTO.getTelephone();
        this.consignorMobile = resDTO.getMobile();
        this.consignorAddr = resDTO.getAddress();
    }

    public void setConsignee(ContactsResDTO resDTO){
        this.consigneeName = resDTO.getName();
        this.consigneeTel = resDTO.getTelephone();
        this.consigneeMobile = resDTO.getMobile();
        this.consigneeAddr = resDTO.getAddress();
    }
}
