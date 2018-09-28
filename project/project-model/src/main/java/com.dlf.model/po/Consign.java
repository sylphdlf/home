package com.dlf.model.po;

import java.math.BigDecimal;
import java.util.Date;

public class Consign {
    private Long id;

    private String consignCode;

    private String departure;

    private String destination;

    private Long consignorId;

    private Long consigneeId;

    private Long vehicleId;

    private Long productId;

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

    public String getConsignCode() {
        return consignCode;
    }

    public void setConsignCode(String consignCode) {
        this.consignCode = consignCode == null ? null : consignCode.trim();
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure == null ? null : departure.trim();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    public Long getConsignorId() {
        return consignorId;
    }

    public void setConsignorId(Long consignorId) {
        this.consignorId = consignorId;
    }

    public Long getConsigneeId() {
        return consigneeId;
    }

    public void setConsigneeId(Long consigneeId) {
        this.consigneeId = consigneeId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getLoadingPlace() {
        return loadingPlace;
    }

    public void setLoadingPlace(String loadingPlace) {
        this.loadingPlace = loadingPlace == null ? null : loadingPlace.trim();
    }

    public Date getVehicleLeaveTime() {
        return vehicleLeaveTime;
    }

    public void setVehicleLeaveTime(Date vehicleLeaveTime) {
        this.vehicleLeaveTime = vehicleLeaveTime;
    }

    public Date getCargoLeaveTime() {
        return cargoLeaveTime;
    }

    public void setCargoLeaveTime(Date cargoLeaveTime) {
        this.cargoLeaveTime = cargoLeaveTime;
    }

    public Date getExpectedArriveTime() {
        return expectedArriveTime;
    }

    public void setExpectedArriveTime(Date expectedArriveTime) {
        this.expectedArriveTime = expectedArriveTime;
    }

    public Date getCargoPackageTime() {
        return cargoPackageTime;
    }

    public void setCargoPackageTime(Date cargoPackageTime) {
        this.cargoPackageTime = cargoPackageTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    public Integer getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(Integer receiptType) {
        this.receiptType = receiptType;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public BigDecimal getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(BigDecimal basicPrice) {
        this.basicPrice = basicPrice;
    }

    public BigDecimal getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(BigDecimal vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public BigDecimal getPayNow() {
        return payNow;
    }

    public void setPayNow(BigDecimal payNow) {
        this.payNow = payNow;
    }

    public BigDecimal getPayDelivery() {
        return payDelivery;
    }

    public void setPayDelivery(BigDecimal payDelivery) {
        this.payDelivery = payDelivery;
    }

    public BigDecimal getPayReceipt() {
        return payReceipt;
    }

    public void setPayReceipt(BigDecimal payReceipt) {
        this.payReceipt = payReceipt;
    }

    public BigDecimal getPayMonthly() {
        return payMonthly;
    }

    public void setPayMonthly(BigDecimal payMonthly) {
        this.payMonthly = payMonthly;
    }

    public BigDecimal getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(BigDecimal payAccount) {
        this.payAccount = payAccount;
    }

    public BigDecimal getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(BigDecimal payTotal) {
        this.payTotal = payTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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