package com.dlf.business.manager.python.impl;

import com.alibaba.fastjson.JSONObject;
import com.dlf.business.dao.JobKeywordMapper;
import com.dlf.business.manager.python.IPythonDataTransferService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.spider.WeatherReqDTO;
import com.dlf.model.po.JobKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/4.
 */
@Service
public class PythonDataTransferService implements IPythonDataTransferService{

    @Autowired
    JobKeywordMapper jobKeywordMapper;

    @Transactional
    public GlobalResultDTO transferDataFromWordSplit(String str) {
        String[] splitStr = str.split(",");
        Map<String,Integer> wordNumMap = new HashMap<String, Integer>();
        for(String thisStr : splitStr){
            if(!StringUtils.isEmpty(thisStr)){
                Integer numTemp = wordNumMap.get(thisStr);
                if(numTemp == null){
                    wordNumMap.put(thisStr, 0);
                }else{
                    wordNumMap.put(thisStr, numTemp + 1);
                }
            }
        }
        if(!CollectionUtils.isEmpty(wordNumMap)){
            Set<Map.Entry<String, Integer>> entries = wordNumMap.entrySet();
            for(Map.Entry<String, Integer> thisEntry : entries){
                JobKeyword jobKeyword = new JobKeyword();
                jobKeyword.setKeyword(thisEntry.getKey());
                jobKeyword.setKeywordNum(thisEntry.getValue());
                jobKeyword.setBatchNum(1);
                jobKeyword.setWebsite("http://www.zhipin.com");
                jobKeywordMapper.insert(jobKeyword);
            }
        }
        return GlobalResultDTO.SUCCESS();
    }

    /**
     * 转换天气数据
     * 格式：{'pm25': '39 优', 'tempCurrent': '31', 'weatherCurrent': '阴', 'websiteUpdateTime': '今天17:00更新', 'humidity': '湿度 76%', 'wind': '东北风1级', 'remark': '很闷热，感觉像在蒸桑拿。'}
     * @param str
     * @return
     */
    public GlobalResultDTO transferDataFromWeather(String str) {
        String str1 = "{'pm25': '39 优', 'tempCurrent': '31', 'weatherCurrent': '阴', 'websiteUpdateTime': '今天17:00更新', 'humidity': '湿度 76%', 'wind': '东北风1级', 'remark': '很闷热，感觉像在蒸桑拿。'}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        WeatherReqDTO reqDTO = new WeatherReqDTO(jsonObject);
        System.out.println(jsonObject.toString());
        return null;
    }

    public static void main(String[] args) {
        String str1 = "{'pm25': '39 优', 'tempCurrent': '31', 'weatherCurrent': '阴', 'websiteUpdateTime': '今天17:00更新', 'humidity': '湿度 76%', 'wind': '东北风1级', 'remark': '很闷热，感觉像在蒸桑拿。'}";
        JSONObject jsonObject = JSONObject.parseObject(str1);

        System.out.println(jsonObject.get("pm25"));
    }
}
