package com.dlf.model.dto.stock;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockMarketReqDTO {

    private Long id;

    private String code;

    private String market;

    private Integer watchingStatus;
}
