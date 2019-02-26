package com.dlf.business.manager.dashboard.impl;

import com.dlf.business.anno.ExecuteTimeAnno;
import com.dlf.business.anno.RedisCacheAnno;
import com.dlf.business.manager.dashboard.DashboardService;
import com.dlf.common.utils.DateTimeUtils;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.chart.ChartJsDTO;
import com.dlf.model.dto.spider.AmapDTO;
import com.dlf.model.dto.spider.AmapSearchDTO;
import com.dlf.model.dto.spider.WeatherDTO;
import com.dlf.model.dto.spider.WeatherSearchDTO;
import com.dlf.model.enums.GlobalResultEnum;
import com.dlf.model.mapper.DashboardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class DashboardServiceImpl implements DashboardService {

    private Logger logger = LoggerFactory.getLogger(DashboardServiceImpl.class);

    @Resource
    DashboardMapper dashboardMapper;

    @Override
    @ExecuteTimeAnno
    @RedisCacheAnno(timeout = 600)
    public GlobalResultDTO get24hWeather(WeatherSearchDTO searchDTO) {
        try {
            Calendar now = Calendar.getInstance();
            now.set(Calendar.HOUR_OF_DAY, -24);
            now.getTime();
            String startTime = DateTimeUtils.timeFormat(now.getTime(), "YYYY-MM-dd HH:mm:ss");
            searchDTO.setStartTime(startTime);
            searchDTO.setCity("浦东新区");
            List<WeatherDTO> weatherList = dashboardMapper.queryWeatherByParams(searchDTO);
            searchDTO.setCity("九江市");
            List<WeatherDTO> weatherList2 = dashboardMapper.queryWeatherByParams(searchDTO);
            if(!CollectionUtils.isEmpty(weatherList)){
                ChartJsDTO chartJsDTO = new ChartJsDTO();
                List<String> label = new ArrayList<String>();
                List<String> data = new ArrayList<String>();
                List<String> data2 = new ArrayList<String>();
                for(WeatherDTO thisDTO : weatherList){
                    label.add(thisDTO.getDataUpdateTime());
                    data.add(thisDTO.getTemperature());
                    data2.add(thisDTO.getPm25());
                }
                chartJsDTO.setLabel(label);
                chartJsDTO.setData(data);
                chartJsDTO.setData2(data2);
                if(!CollectionUtils.isEmpty(weatherList2)){
                    List<String> data3 = new ArrayList<String>();
                    List<String> data4 = new ArrayList<String>();
                    for(WeatherDTO thisDTO : weatherList2){
                        data3.add(thisDTO.getTemperature());
                        data4.add(thisDTO.getPm25());
                    }
                    chartJsDTO.setData3(data3);
                    chartJsDTO.setData4(data4);
                }
                return new GlobalResultDTO(chartJsDTO);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getMsg());
    }

    @Override
    public GlobalResultDTO get24hPm25(WeatherSearchDTO searchDTO) {
        try {
            Calendar now = Calendar.getInstance();
            now.add(Calendar.HOUR_OF_DAY, -24);
            now.getTime();
            String startTime = DateTimeUtils.timeFormat(now.getTime(), "YYYY-MM-dd HH:mm:ss");
            searchDTO.setStartTime(startTime);
            List<WeatherDTO> weatherList = dashboardMapper.queryPm25ByParams(searchDTO);
            return new GlobalResultDTO(weatherList);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getMsg());
    }

    @Override
    @RedisCacheAnno(timeout = 600)
    public GlobalResultDTO getMorningTraffic(AmapSearchDTO searchDTO) {
        try {
            Calendar startCal = Calendar.getInstance();
            startCal.set(Calendar.HOUR_OF_DAY, 7);
            startCal.set(Calendar.MINUTE, 0);
            startCal.set(Calendar.SECOND, 0);
            String startTime = DateTimeUtils.timeFormat(startCal.getTime(), "YYYY-MM-dd HH:mm:ss");
            Calendar endCal = Calendar.getInstance();
            endCal.set(Calendar.HOUR_OF_DAY, 9);
            endCal.set(Calendar.MINUTE, 0);
            endCal.set(Calendar.SECOND, 0);
            String endTime = DateTimeUtils.timeFormat(endCal.getTime(), "YYYY-MM-dd HH:mm:ss");
            //赋值
            searchDTO.setStartTime(startTime);
            searchDTO.setEndTime(endTime);

            List<AmapDTO> trafficList = dashboardMapper.getTraffic(searchDTO);
            if(!CollectionUtils.isEmpty(trafficList)){
                ChartJsDTO chartJsDTO = new ChartJsDTO();
                //用于去重
                Set<String> isRepeat = new HashSet<String>();
                List<String> label = new ArrayList<String>();
                List<String> data = new ArrayList<String>();
                List<String> data2 = new ArrayList<String>();
                List<String> data3 = new ArrayList<String>();
                for(AmapDTO thisDTO : trafficList){
                    if(isRepeat.add(thisDTO.getCreateTime())){
                        label.add(thisDTO.getCreateTime());
                    }
                    if(thisDTO.getSearchCode().equals("home-hxzl-office")){
                        data.add(thisDTO.getTimeCost());
                    }else if(thisDTO.getSearchCode().equals("tlgl-hxzl")){
                        data2.add(thisDTO.getTimeCost());
                    }else if(thisDTO.getSearchCode().equals("hxzl-zjl")){
                        data3.add(thisDTO.getTimeCost());
                    }

                }
                chartJsDTO.setLabel(label);
                chartJsDTO.setData(data);
                chartJsDTO.setData2(data2);
                chartJsDTO.setData3(data3);
                return new GlobalResultDTO(chartJsDTO);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getMsg());
    }
}
