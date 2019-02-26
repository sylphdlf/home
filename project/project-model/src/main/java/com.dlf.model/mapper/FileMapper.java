package com.dlf.model.mapper;

import com.dlf.model.po.File;

public interface FileMapper extends BaseMapper{
    int deleteByPrimaryKey(Long id);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);
}