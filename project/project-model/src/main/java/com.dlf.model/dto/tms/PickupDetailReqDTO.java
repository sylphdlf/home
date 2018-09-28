package com.dlf.model.dto.tms;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PickupDetailReqDTO {

    private Long consignId;

    private BigDecimal price;

    private String remarks;
}
