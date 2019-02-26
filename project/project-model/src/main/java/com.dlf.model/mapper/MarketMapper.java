package com.dlf.model.mapper;

import com.dlf.model.po.Market;

public interface MarketMapper extends BaseMapper{
    int deleteByPrimaryKey(Long id);

    int insert(Market record);

    int insertSelective(Market record);

    Market selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Market record);

    int updateByPrimaryKey(Market record);
}