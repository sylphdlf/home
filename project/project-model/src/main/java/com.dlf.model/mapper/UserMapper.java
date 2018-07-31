package com.dlf.model.mapper;

import com.dlf.model.dto.user.UserSearchDTO;
import com.dlf.model.po.User;

public interface UserMapper extends BaseMapper<UserSearchDTO> {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}