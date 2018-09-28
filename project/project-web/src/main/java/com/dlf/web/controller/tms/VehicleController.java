package com.dlf.web.controller.tms;

import com.dlf.business.manager.tms.ContactsService;
import com.dlf.business.manager.tms.VehicleService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.tms.ContactsReqDTO;
import com.dlf.model.dto.tms.ContactsSearchDTO;
import com.dlf.model.dto.tms.VehicleReqDTO;
import com.dlf.model.dto.tms.VehicleSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    /**
     * 分页查询
     * @param searchDTO
     * @return
     */
    @RequestMapping(value = "/queryPageByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryPageByParams(@RequestBody VehicleSearchDTO searchDTO) {
        return vehicleService.queryPageByParams(searchDTO);
    }

    /**
     * 新增
     * @param reqDTO
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public GlobalResultDTO add(@RequestBody VehicleReqDTO reqDTO){
        return vehicleService.add(reqDTO);
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public GlobalResultDTO del(@RequestBody VehicleReqDTO reqDTO){
        return vehicleService.del(reqDTO);
    }
}
