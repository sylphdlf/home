package com.dlf.model.dto.stock;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MarketInfoSearchDTO extends PageDTO {

    private String code;

    private Date refreshTimeStart;
    private Date refreshTimeEnd;

    private String[] searchKeys;
}
