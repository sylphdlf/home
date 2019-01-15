package com.dlf.web.controller.user;

import com.dlf.business.anno.FunDescriptionAnno;
import com.dlf.business.anno.MenuDescriptionAnno;
import com.dlf.business.manager.user.RoleService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.OrgReqDTO;
import com.dlf.model.dto.user.RoleReqDTO;
import com.dlf.model.dto.user.RoleSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 */
@RestController
@RequestMapping("/role")
@MenuDescriptionAnno(name = "角色管理", parent = "用户管理")
public class RoleController {

    @Autowired
    RoleService roleService;

    @FunDescriptionAnno(name = "分页查询角色列表")
    @RequestMapping(value = "/queryListByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryListByParams(@RequestBody RoleSearchDTO searchDTO) {
        return roleService.queryListByParams(searchDTO);
    }

    @FunDescriptionAnno(name = "新增角色")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public GlobalResultDTO add(@RequestBody RoleReqDTO reqDTO){
        return roleService.add(reqDTO);
    }

    @FunDescriptionAnno(name = "绑定角色权限")
    @RequestMapping(value = "/bindingFun", method = RequestMethod.POST)
    public GlobalResultDTO bindingFun(@RequestBody RoleReqDTO reqDTO){
        return roleService.bindingFun(reqDTO);
    }

    @FunDescriptionAnno(name = "删除")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public GlobalResultDTO del(@RequestBody RoleReqDTO reqDTO){
        return roleService.del(reqDTO);
    }
}
