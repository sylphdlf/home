package com.dlf.business.dao;

import com.dlf.model.po.JobKeyword;

public interface JobKeywordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JobKeyword record);

    int insertSelective(JobKeyword record);

    JobKeyword selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JobKeyword record);

    int updateByPrimaryKey(JobKeyword record);
}