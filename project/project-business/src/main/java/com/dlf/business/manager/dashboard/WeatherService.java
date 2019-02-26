package com.dlf.business.manager.dashboard;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.spider.WeatherDTO;
import com.dlf.model.dto.spider.WeatherSearchDTO;

public interface WeatherService {
    /**
     * 获取最新的天气
     * @param searchDTO
     * @return
     */
    GlobalResultDTO<WeatherDTO> getLastWeather(WeatherSearchDTO searchDTO);
}
