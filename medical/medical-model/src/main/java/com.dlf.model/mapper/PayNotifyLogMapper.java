package com.dlf.model.mapper;

import com.dlf.model.po.PayNotifyLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayNotifyLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PayNotifyLog record);

    int insertSelective(PayNotifyLog record);

    PayNotifyLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PayNotifyLog record);

    int updateByPrimaryKey(PayNotifyLog record);
}