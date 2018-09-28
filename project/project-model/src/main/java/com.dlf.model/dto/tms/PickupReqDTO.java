package com.dlf.model.dto.tms;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PickupReqDTO {

    private Integer site;

    private Long vehicleId;

    private BigDecimal price;

    private Date pickupTime;

    private String remarks;

    private List<PickupDetailReqDTO> list;
}
