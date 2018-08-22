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
    FunService funService;
    /**
     * 下拉列表级联展示菜单
     */
    @RequestMapping(value = "/getDefaultMenu", method = RequestMethod.GET)
    public GlobalResultDTO getDefaultMenu(){
        return funService.getDefaultMenu();
    }

    /**
     * 下拉列表级联展示资源
     * @return
     */
    @RequestMapping(value = "/getDefaultSource", method = RequestMethod.GET)
    public GlobalResultDTO getDefaultSource(){
        return funService.getDefaultSource();
    }
    /**
     * 查询菜单
     * @param searchDTO
     * @return
     */
    @RequestMapping(value = "/queryMenuByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryMenuByParams(@RequestBody FunSearchDTO searchDTO) {
        return funService.queryMenuByParams(searchDTO);
    }

    /**
     * 查询子权限
     * @param searchDTO
     * @return
     */
    @RequestMapping(value = "/queryFunByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryFunByParams(@RequestBody FunSearchDTO searchDTO){
        return funService.queryFunByParams(searchDTO);
    }

    /**
     * 新增权限
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public GlobalResultDTO add(@RequestBody FunReqDTO reqDTO){
        return funService.add(reqDTO);
    }

    /**
     * 获取组织机构树
     * @return
     */
    @RequestMapping(value = "/getFunTree",method = RequestMethod.POST)
    public GlobalResultDTO getFunTree(@RequestBody FunReqDTO reqDTO) {
        return funService.getFunTree(reqDTO);
    }
}
