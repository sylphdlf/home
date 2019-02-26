package com.dlf.model.dto.spider;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AmapSearchDTO {

    private String startTime;
    private String endTime;

    private String searchCode;
    private String distance;

}
