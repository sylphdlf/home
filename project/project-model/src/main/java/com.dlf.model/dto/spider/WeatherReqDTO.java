package com.dlf.model.dto.spider;

import com.alibaba.fastjson.JSONObject;
import com.dlf.model.utils.DateTimeUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 天气
 * Date: 2017/8/21
 *
 * @author dailf@zendaimoney.com
 */
@NoArgsConstructor
@Data
public class WeatherReqDTO implements Serializable{

    private Long id;

    private String country;

    private String province;

    private String city;

    private String weatherCurrent;

    private String weatherWholeDay;
    //当前天气
    private Integer tempCurrent;

    private Integer tempMax;

    private Integer tempMin;

    private Integer humidity;

    private String wind;

    private Date websiteUpdateTime;

    private Date createTime;

    private String remark;

    public WeatherReqDTO(JSONObject jsonObject) {
        if(StringUtils.isEmpty(jsonObject.get("tempCurrent"))){
            this.tempCurrent = Integer.valueOf(jsonObject.get("tempCurrent") + "");
        }
        if(StringUtils.isEmpty(jsonObject.get("weatherCurrent"))){
            this.weatherCurrent = jsonObject.get("weatherCurrent") + "";
        }
        String websiteUpdateTime = jsonObject.get("websiteUpdateTime") + "";
        if(StringUtils.isEmpty(websiteUpdateTime)){
            //去掉中文并转换为时间
            Pattern pattern = Pattern.compile("\\d+:\\d+");
            Matcher matcher = pattern.matcher(websiteUpdateTime);
            if(matcher.find()){
                String hourAndMinute = matcher.group();
                this.websiteUpdateTime = DateTimeUtils.transferStrToDate(hourAndMinute);
            }

        }
//        if(StringUtils.isEmpty(jsonObject.get())){
//
//        }
//        if(StringUtils.isEmpty(jsonObject.get())){
//
//        }
//        if(StringUtils.isEmpty(jsonObject.get())){
//
//        }
//        if(StringUtils.isEmpty(jsonObject.get())){
//
//        }
    }
}
