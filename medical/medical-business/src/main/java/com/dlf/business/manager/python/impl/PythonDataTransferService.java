package com.dlf.business.manager.python.impl;

import com.dlf.business.manager.python.IPythonDataTransferService;
import com.dlf.model.dto.GlobalResultDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/4.
 */
@Service
public class PythonDataTransferService implements IPythonDataTransferService{

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
        return null;
    }
}
