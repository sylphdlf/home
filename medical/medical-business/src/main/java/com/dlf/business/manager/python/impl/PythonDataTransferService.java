package com.dlf.business.manager.python.impl;

import com.dlf.business.dao.JobKeywordMapper;
import com.dlf.business.manager.python.IPythonDataTransferService;
import com.dlf.model.dto.GlobalResultDTO;
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
     * 格式：[['21日（今天）', '多云', '34', '27'], ['22日（明天）', '多云', '34', '27']]
     * @param str
     * @return
     */
    public GlobalResultDTO transferDataFromWeather(String str) {

        return null;
    }
}
