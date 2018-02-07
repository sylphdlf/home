package com.dlf.business.dao;

import com.dlf.model.po.JobKeyword;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobKeywordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JobKeyword record);

    int insertSelective(JobKeyword record);

    JobKeyword selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JobKeyword record);

    int updateByPrimaryKey(JobKeyword record);
}