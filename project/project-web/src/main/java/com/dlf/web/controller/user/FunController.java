package com.dlf.web.controller.user;

import com.dlf.business.anno.FunDescriptionAnno;
import com.dlf.business.anno.MenuDescriptionAnno;
import com.dlf.business.exception.MyException;
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
@MenuDescriptionAnno(name = "权限管理", parent = "用户管理")
public class FunController {

    @Autowired
    FunService funService;

    @FunDescriptionAnno(name = "展示菜单列表")
    @RequestMapping(value = "/getMenu", method = RequestMethod.POST)
    public GlobalResultDTO getMenu(@RequestBody FunReqDTO reqDTO){
        return funService.getMenu(reqDTO);
    }

    @FunDescriptionAnno(name = "绑定二级菜单")
    @RequestMapping(value = "/bindingMenuByUser", method = RequestMethod.POST)
    public GlobalResultDTO bindingMenuByUser(@RequestBody FunReqDTO reqDTO){
        return funService.bindingMenuByUser(reqDTO);
    }

    @FunDescriptionAnno(name = "展示资源列表")
    @RequestMapping(value = "/getDefaultSource", method = RequestMethod.GET)
    public GlobalResultDTO getDefaultSource(){
        return funService.getDefaultSource();
    }

    @FunDescriptionAnno(name = "查询菜单列表")
    @RequestMapping(value = "/queryMenuByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryMenuByParams(@RequestBody FunSearchDTO searchDTO) {
        return funService.queryMenuByParams(searchDTO);
    }

    @FunDescriptionAnno(name = "查询子权限")
    @RequestMapping(value = "/queryFunByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryFunByParams(@RequestBody FunSearchDTO searchDTO){
        return funService.queryFunByParams(searchDTO);
    }
    @FunDescriptionAnno(name = "新增一级菜单")
    @RequestMapping(value = "/addMenu", method = RequestMethod.POST)
    public GlobalResultDTO addMenu(@RequestBody FunReqDTO reqDTO){
        return funService.addMenu(reqDTO);
    }

    @FunDescriptionAnno(name = "新增权限")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public GlobalResultDTO add(@RequestBody FunReqDTO reqDTO){
        return funService.add(reqDTO);
    }

    @FunDescriptionAnno(name = "获取组织机构树")
    @RequestMapping(value = "/getFunTree",method = RequestMethod.POST)
    public GlobalResultDTO getFunTree(@RequestBody FunReqDTO reqDTO) {
        return funService.getFunTree(reqDTO);
    }
}
