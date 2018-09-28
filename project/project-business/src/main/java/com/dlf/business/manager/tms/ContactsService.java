package com.dlf.business.manager.tms;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.tms.ContactsReqDTO;
import com.dlf.model.dto.tms.ContactsSearchDTO;

public interface ContactsService {
    /**
     * 分页查询
     * @param searchDTO
     * @return
     */
    GlobalResultDTO queryPageByParams(ContactsSearchDTO searchDTO);

    /**
     * 新增
     * @param reqDTO
     * @return
     */
    GlobalResultDTO add(ContactsReqDTO reqDTO);

    /**
     * 删除
     * @param reqDTO
     * @return
     */
    GlobalResultDTO del(ContactsReqDTO reqDTO);

    /**
     * 分页查询发货人
     * @param searchDTO
     * @return
     */
    GlobalResultDTO queryConsignorPage(ContactsSearchDTO searchDTO);

    /**
     * 分页查询收货人
     * @param searchDTO
     * @return
     */
    GlobalResultDTO queryConsigneePage(ContactsSearchDTO searchDTO);

    /**
     * 获取联系人记录
     * @param reqDTO
     * @return
     */
    GlobalResultDTO getContactsRecord(ContactsReqDTO reqDTO);
}
