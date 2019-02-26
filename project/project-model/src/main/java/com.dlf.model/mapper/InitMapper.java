package com.dlf.model.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InitMapper {

    void initTable(JSONObject initJson);
}
