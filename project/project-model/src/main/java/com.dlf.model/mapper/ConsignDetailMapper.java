package com.dlf.model.mapper;

import com.dlf.model.po.ConsignDetail;

public interface ConsignDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ConsignDetail record);

    int insertSelective(ConsignDetail record);

    ConsignDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ConsignDetail record);

    int updateByPrimaryKey(ConsignDetail record);
}