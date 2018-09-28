package com.dlf.model.mapper;

import com.dlf.model.po.Consign;

public interface ConsignMapper extends BaseMapper{
    int deleteByPrimaryKey(Long id);

    int insert(Consign record);

    int insertSelective(Consign record);

    Consign selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Consign record);

    int updateByPrimaryKey(Consign record);
}