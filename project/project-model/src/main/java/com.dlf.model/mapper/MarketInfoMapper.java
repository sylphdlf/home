package com.dlf.model.mapper;

import com.dlf.model.po.MarketInfo;

public interface MarketInfoMapper extends BaseMapper{
    int deleteByPrimaryKey(Long id);

    int insert(MarketInfo record);

    int insertSelective(MarketInfo record);

    MarketInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MarketInfo record);

    int updateByPrimaryKey(MarketInfo record);
}