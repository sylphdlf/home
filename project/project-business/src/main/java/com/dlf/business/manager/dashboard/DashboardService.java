package com.dlf.business.manager.dashboard;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.spider.AmapSearchDTO;
import com.dlf.model.dto.spider.WeatherSearchDTO;

public interface DashboardService {
    GlobalResultDTO get24hWeather(WeatherSearchDTO searchDTO);

    GlobalResultDTO get24hPm25(WeatherSearchDTO searchDTO);

    GlobalResultDTO getMorningTraffic(AmapSearchDTO searchDTO);
}
