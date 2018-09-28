package com.dlf.business.manager.tms.impl;

import com.dlf.business.manager.tms.ContactsService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.tms.ContactsDTO;
import com.dlf.model.dto.tms.ContactsReqDTO;
import com.dlf.model.dto.tms.ContactsResDTO;
import com.dlf.model.dto.tms.ContactsSearchDTO;
import com.dlf.model.enums.GlobalResultEnum;
import com.dlf.model.enums.tms.ConsignEnums;
import com.dlf.model.mapper.ContactsMapper2;
import com.dlf.model.po.Contacts;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ContactsServiceImpl implements ContactsService {

    private Logger logger = LoggerFactory.getLogger(ContactsServiceImpl.class);

    @Resource
    private ContactsMapper2 contactsMapper;

    @Override
    public GlobalResultDTO queryPageByParams(ContactsSearchDTO searchDTO) {
        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        List<ContactsDTO> list = contactsMapper.queryListByParams(searchDTO);
        PageInfo<ContactsDTO> pageInfo = new PageInfo<ContactsDTO>(list);
        return new GlobalResultDTO<PageInfo>(pageInfo);
    }

    @Override
    public GlobalResultDTO queryConsignorPage(ContactsSearchDTO searchDTO) {
        searchDTO.setType(ConsignEnums.CONTACTS_TYPE_1.getCode());
        return this.queryPageByParams(searchDTO);
    }

    @Override
    public GlobalResultDTO queryConsigneePage(ContactsSearchDTO searchDTO) {
        searchDTO.setType(ConsignEnums.CONTACTS_TYPE_2.getCode());
        return this.queryPageByParams(searchDTO);
    }

    @Override
    public GlobalResultDTO getContactsRecord(ContactsReqDTO reqDTO) {
        try {
            Contacts contacts = contactsMapper.selectByPrimaryKey(reqDTO.getId());
            ContactsResDTO resDTO = new ContactsResDTO();
            BeanUtils.copyProperties(contacts, resDTO);
            return new GlobalResultDTO<ContactsResDTO>(resDTO);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode(), GlobalResultEnum.FAIL.getMsg());
    }

    @Override
    public GlobalResultDTO add(ContactsReqDTO reqDTO) {
        //FIXME 验证
        Contacts contacts = new Contacts();
        BeanUtils.copyProperties(reqDTO, contacts);
        try {
            int count = contactsMapper.insert(contacts);
            if(count == 1){
                return GlobalResultDTO.SUCCESS();
            }else{
                return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode());
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode(),GlobalResultEnum.FAIL.getMsg());
        }
    }

    @Override
    public GlobalResultDTO del(ContactsReqDTO reqDTO) {
        try {
            Contacts contacts = new Contacts();
            BeanUtils.copyProperties(reqDTO, contacts);
            int count = contactsMapper.deleteSoft(contacts);
            if(count == 1){
                return GlobalResultDTO.SUCCESS();
            }
            return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode(), GlobalResultEnum.FAIL.getMsg());
        }catch (Exception e){
            logger.error(e.getMessage());
            return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode(), GlobalResultEnum.FAIL.getMsg());
        }
    }
}
