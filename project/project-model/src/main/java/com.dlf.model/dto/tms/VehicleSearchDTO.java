package com.dlf.model.dto.tms;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleSearchDTO extends PageDTO {

    private String vehicleNumber;
}
