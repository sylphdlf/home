package com.dlf.model.mapper;

import com.dlf.model.po.Function;

public interface FunctionMapper extends BaseMapper{
    int deleteByPrimaryKey(Long id);

    int insert(Function record);

    int insertSelective(Function record);

    Function selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);
}