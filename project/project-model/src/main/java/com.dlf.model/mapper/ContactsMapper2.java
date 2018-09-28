package com.dlf.model.mapper;

import com.dlf.model.dto.tms.ContactsDTO;
import com.dlf.model.dto.tms.ContactsReqDTO;
import com.dlf.model.dto.tms.ContactsSearchDTO;
import com.dlf.model.po.Contacts;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContactsMapper2 extends ContactsMapper{
    /**
     * 查询列表
     * @param searchDTO
     * @return
     */
    List<ContactsDTO> queryListByParams(ContactsSearchDTO searchDTO);

    /**
     * 软删除
     * @param contacts
     * @return
     */
    int deleteSoft(Contacts contacts);
}