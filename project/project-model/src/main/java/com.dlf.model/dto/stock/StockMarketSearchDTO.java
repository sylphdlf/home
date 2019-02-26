package com.dlf.model.dto.stock;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockMarketSearchDTO extends PageDTO {

    private String code;
    private String name;
    private String market;
    private Integer watchingStatus;
}
