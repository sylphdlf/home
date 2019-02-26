package com.dlf.model.dto.spider;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherSearchDTO {

    private String city;

    private String startTime;
}
