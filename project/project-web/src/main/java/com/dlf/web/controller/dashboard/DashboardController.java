package com.dlf.web.controller.dashboard;

import com.dlf.business.anno.MenuDescriptionAnno;
import com.dlf.business.manager.dashboard.DashboardService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.spider.AmapSearchDTO;
import com.dlf.model.dto.spider.WeatherSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 看板
 */
@RestController
@RequestMapping("/dashboard")
@MenuDescriptionAnno(name = "看板", parent = "dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @RequestMapping(value = "/get24hWeather",method = RequestMethod.GET)
    public GlobalResultDTO get24hTemp(){
        WeatherSearchDTO searchDTO = new WeatherSearchDTO();
        return dashboardService.get24hWeather(searchDTO);
    }

    @RequestMapping(value = "/get24hWeatherTest",method = RequestMethod.GET)
    public GlobalResultDTO get24hWeatherTest(){
        return GlobalResultDTO.SUCCESS();
    }

    @Deprecated
    @RequestMapping(value = "/get24hPm25",method = RequestMethod.POST)
    public GlobalResultDTO get24hPm25(@RequestBody WeatherSearchDTO searchDTO){
        return dashboardService.get24hPm25(searchDTO);
    }
    @RequestMapping(value = "/getMorningTraffic", method = RequestMethod.POST)
    public GlobalResultDTO getMorningTraffic(@RequestBody AmapSearchDTO searchDTO){
        return dashboardService.getMorningTraffic(searchDTO);
    }
}
