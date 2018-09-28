package com.dlf.model.dto.tms;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PickupDTO {

    private Long id;

    private String pickupCode;

    private Integer site;

    private BigDecimal price;

    private Integer count;

    private Date pickupTime;

    private String remarks;

    //车辆信息

    private String vehicleNumber;

    private String driverName;

    private String driverMobile;
}
