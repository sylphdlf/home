package com.dlf.web.controller.tms;

import com.dlf.business.manager.tms.ContactsService;
import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.tms.ContactsReqDTO;
import com.dlf.model.dto.tms.ContactsSearchDTO;
import com.dlf.model.dto.user.UserSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 */
@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    ContactsService contactsService;

    /**
     * 分页查询
     * @param searchDTO
     * @return
     */
    @RequestMapping(value = "/queryPageByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryPageByParams(@RequestBody ContactsSearchDTO searchDTO) {
        return contactsService.queryPageByParams(searchDTO);
    }

    /**
     * 查询发货人
     * @param searchDTO
     * @return
     */
    @RequestMapping(value = "/queryConsignorPage",method = RequestMethod.POST)
    public GlobalResultDTO queryConsignorPage(@RequestBody ContactsSearchDTO searchDTO){
        return contactsService.queryConsignorPage(searchDTO);
    }

    /**
     * 查询收货人
     * @param searchDTO
     * @return
     */
    @RequestMapping(value = "/queryConsigneePage",method = RequestMethod.POST)
    public GlobalResultDTO queryConsigneePage(@RequestBody ContactsSearchDTO searchDTO){
        return contactsService.queryConsigneePage(searchDTO);
    }
    /**
     * 新增
     * @param reqDTO
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public GlobalResultDTO add(@RequestBody ContactsReqDTO reqDTO){
        return contactsService.add(reqDTO);
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public GlobalResultDTO del(@RequestBody ContactsReqDTO reqDTO){
        return contactsService.del(reqDTO);
    }
}
