package com.dlf.web.controller.tms;

import com.dlf.business.anno.FunDescriptionAnno;
import com.dlf.business.anno.MenuDescriptionAnno;
import com.dlf.business.manager.tms.ConsignService;
import com.dlf.business.manager.tms.PickupService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.tms.ConsignReqDTO;
import com.dlf.model.dto.tms.ConsignSearchDTO;
import com.dlf.model.dto.tms.PickupReqDTO;
import com.dlf.model.dto.tms.PickupSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 */
@RestController
@RequestMapping("/pickup")
@MenuDescriptionAnno(name = "上门接货", parent = "物流管理")
public class PickupController {

    @Autowired
    PickupService pickupService;

    @FunDescriptionAnno(name = "查询")
    @RequestMapping(value = "/queryPageByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryPageByParams(@RequestBody PickupSearchDTO searchDTO) {
        return pickupService.queryPageByParams(searchDTO);
    }

    /**
     * 新增
     * @param reqDTO
     * @return
     */
    @FunDescriptionAnno(name = "新增")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public GlobalResultDTO add(@RequestBody PickupReqDTO reqDTO){
        return pickupService.add(reqDTO);
    }
}
