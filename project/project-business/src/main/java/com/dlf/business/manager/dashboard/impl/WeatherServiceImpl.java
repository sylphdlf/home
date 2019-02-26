package com.dlf.business.manager.dashboard.impl;

import com.dlf.business.manager.dashboard.WeatherService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.spider.WeatherDTO;
import com.dlf.model.dto.spider.WeatherSearchDTO;
import com.dlf.model.mapper.DashboardMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Resource
    DashboardMapper dashboardMapper;

    @Override
    public GlobalResultDTO<WeatherDTO> getLastWeather(WeatherSearchDTO searchDTO) {
        return new GlobalResultDTO<WeatherDTO>(dashboardMapper.getLastWeather(searchDTO));
    }
}
