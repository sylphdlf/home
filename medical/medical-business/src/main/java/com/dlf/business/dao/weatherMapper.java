package com.dlf.business.dao;

import com.dlf.model.po.weather;

public interface weatherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(weather record);

    int insertSelective(weather record);

    weather selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(weather record);

    int updateByPrimaryKey(weather record);
}