package com.dlf.model.mapper;

import com.dlf.model.po.Pickup;

public interface PickupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Pickup record);

    int insertSelective(Pickup record);

    Pickup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Pickup record);

    int updateByPrimaryKey(Pickup record);
}