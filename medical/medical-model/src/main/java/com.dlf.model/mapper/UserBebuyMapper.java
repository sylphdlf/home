package com.dlf.model.mapper;

import com.dlf.model.po.UserBebuy;
import org.apache.ibatis.annotations.Mapper;

public interface UserBebuyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserBebuy record);

    int insertSelective(UserBebuy record);

    UserBebuy selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserBebuy record);

    int updateByPrimaryKey(UserBebuy record);
}