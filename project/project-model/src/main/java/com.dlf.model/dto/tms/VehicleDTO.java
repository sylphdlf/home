package com.dlf.model.dto.tms;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class VehicleDTO {

    private Long id;

    private String vehicleNumber;

    private Integer vehicleType;

    private Integer vehicleLength;

    private Integer vehicleWidth;

    private Integer vehicleHeight;

    private String vehicleVin;

    private String driverName;

    private String driverMobile;

    private String driverTelephone;

    private String driverAddr;

    private String driverLicence;

    private String ownerName;

    private String ownerMobile;

    private String accountBank;

    private String accountName;

    private String accountCard;

    private String remarks;

    private Date createTime;
}
