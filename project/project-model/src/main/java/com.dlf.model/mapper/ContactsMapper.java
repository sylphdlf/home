package com.dlf.model.mapper;

import com.dlf.model.po.Contacts;

public interface ContactsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Contacts record);

    int insertSelective(Contacts record);

    Contacts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Contacts record);

    int updateByPrimaryKey(Contacts record);
}