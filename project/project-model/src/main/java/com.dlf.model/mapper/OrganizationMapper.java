package com.dlf.model.mapper;

import com.dlf.model.po.Organization;

public interface OrganizationMapper extends BaseMapper{
    int deleteByPrimaryKey(Long id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
}