package com.dlf.model.mapper;

import com.dlf.model.po.UserBebuy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserBebuyMapper2 extends UserBebuyMapper{
    /**
     * 通过用户名获取用户
     * @param userBebuy
     * @return
     */
    UserBebuy selectByUsername(UserBebuy userBebuy);
}