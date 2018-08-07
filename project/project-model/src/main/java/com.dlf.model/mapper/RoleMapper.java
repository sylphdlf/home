package com.dlf.model.mapper;

import com.dlf.model.po.Role;

public interface RoleMapper extends BaseMapper{
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}