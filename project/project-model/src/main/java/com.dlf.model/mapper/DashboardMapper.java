package com.dlf.model.mapper;

import com.dlf.model.dto.spider.AmapDTO;
import com.dlf.model.dto.spider.AmapSearchDTO;
import com.dlf.model.dto.spider.WeatherDTO;
import com.dlf.model.dto.spider.WeatherSearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DashboardMapper {

    WeatherDTO getLastWeather(WeatherSearchDTO searchDTO);

    List<WeatherDTO> queryWeatherByParams(WeatherSearchDTO searchDTO);

    List<WeatherDTO> queryPm25ByParams(WeatherSearchDTO searchDTO);

    List<AmapDTO> getTraffic(AmapSearchDTO searchDTO);

}