package com.dlf.web.controller.user;

import com.dlf.business.manager.user.FunService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.FunReqDTO;
import com.dlf.model.dto.user.FunSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 */
@RestController
@RequestMapping("/fun")
public class FunController {

    @Autowired
    FunService functionService;
    /**
     * 下拉列表级联展示菜单
     */
    @RequestMapping(value = "/getDefaultMenu", method = RequestMethod.GET)
    public GlobalResultDTO getDefaultMenu(){
        return functionService.getDefaultMenu();
    }

    /**
     * 下拉列表级联展示资源
     * @return
     */
    @RequestMapping(value = "/getDefaultSource", method = RequestMethod.GET)
    public GlobalResultDTO getDefaultSource(){
        return functionService.getDefaultSource();
    }
    /**
     * 查询菜单
     * @param searchDTO
     * @return
     */
    @RequestMapping(value = "/queryMenuByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryMenuByParams(@RequestBody FunSearchDTO searchDTO) {
        return functionService.queryMenuByParams(searchDTO);
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public GlobalResultDTO add(@RequestBody FunReqDTO reqDTO){
        return functionService.add(reqDTO);
    }
}
