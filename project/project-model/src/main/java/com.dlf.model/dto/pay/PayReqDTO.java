package com.dlf.model.dto.pay;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PayReqDTO{

    private String channel;

    private String amount;
}
