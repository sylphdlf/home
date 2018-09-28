package com.dlf.web.controller.tms;

import com.dlf.business.manager.tms.ConsignService;
import com.dlf.business.manager.tms.ContactsService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.tms.ConsignReqDTO;
import com.dlf.model.dto.tms.ConsignSearchDTO;
import com.dlf.model.dto.tms.ContactsReqDTO;
import com.dlf.model.dto.tms.ContactsSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 */
@RestController
@RequestMapping("/consign")
public class ConsignController {

    @Autowired
    ConsignService consignService;

    /**
     * 分页查询
     * @param searchDTO
     * @return
     */
    @RequestMapping(value = "/queryPageByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryPageByParams(@RequestBody ConsignSearchDTO searchDTO) {
        return consignService.queryPageByParams(searchDTO);
    }
    /**
     * 分页查询(子表左连接)
     * @param searchDTO
     * @return
     */
    @RequestMapping(value = "/queryDetailPageByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryDetailPageByParams(@RequestBody ConsignSearchDTO searchDTO) {
        return consignService.queryDetailPageByParams(searchDTO);
    }

    /**
     * 新增（第二步)
     * @param reqDTO
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public GlobalResultDTO add(@RequestBody ConsignReqDTO reqDTO){
        return consignService.add(reqDTO);
    }

    /**
     * 新增（第一步)
     * @param reqDTO
     * @return
     */
//    @RequestMapping(value = "/addStep1",method = RequestMethod.POST)
//    public GlobalResultDTO addStep1(@RequestBody ConsignReqDTO reqDTO){
//        return consignService.addStep1(reqDTO);
//    }

    /**
     * 新增（第二步)
     * @param reqDTO
     * @return
     */
//    @RequestMapping(value = "/addStep2",method = RequestMethod.POST)
//    public GlobalResultDTO addStep2(@RequestBody ConsignReqDTO reqDTO){
//        return consignService.addStep2(reqDTO);
//    }

    /**
     * 获取托运单记录(单条)
     * @param reqDTO
     * @return
     */
    @RequestMapping(value = "/getConsignRecord",method = RequestMethod.POST)
    public GlobalResultDTO getConsignRecord(@RequestBody ConsignReqDTO reqDTO){
        return consignService.getConsignRecord(reqDTO);
    }
}
