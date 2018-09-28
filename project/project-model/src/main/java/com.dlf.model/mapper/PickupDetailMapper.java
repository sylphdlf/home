package com.dlf.model.mapper;

import com.dlf.model.po.PickupDetail;

public interface PickupDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PickupDetail record);

    int insertSelective(PickupDetail record);

    PickupDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PickupDetail record);

    int updateByPrimaryKey(PickupDetail record);
}