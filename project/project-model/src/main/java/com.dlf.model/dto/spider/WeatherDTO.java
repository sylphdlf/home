package com.dlf.model.dto.spider;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherDTO {

    private String dataUpdateTime;
    private String temperature;
    private String pm25;
    private String city;
    private String weather;
    private String humidity;
}
