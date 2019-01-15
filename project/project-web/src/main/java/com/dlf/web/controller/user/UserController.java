package com.dlf.web.controller.user;

import com.dlf.business.anno.FunDescriptionAnno;
import com.dlf.business.anno.MenuDescriptionAnno;
import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserReqDTO;
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
@RequestMapping("/user")
@MenuDescriptionAnno(name = "用户列表", parent = "用户管理")
public class UserController {

    @Autowired
    UserService userService;

    @FunDescriptionAnno(name = "查询")
    @RequestMapping(value = "/queryListByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryListByParams(@RequestBody UserSearchDTO searchDTO) {
        return userService.queryListByParams(searchDTO);
    }

    @FunDescriptionAnno(name = "获取用户的角色")
    @RequestMapping(value = "/getRolePageByUser", method = RequestMethod.POST)
    public GlobalResultDTO getRolePageByUser(@RequestBody UserSearchDTO searchDTO){
        return userService.getRolePageByUser(searchDTO);
    }

    @FunDescriptionAnno(name = "用户绑定角色")
    @RequestMapping(value = "/bindingRole", method = RequestMethod.POST)
    public GlobalResultDTO bindingRole(@RequestBody UserReqDTO reqDTO){
        return userService.bindingRole(reqDTO);
    }

    @FunDescriptionAnno(name = "删除用户(硬删除)")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public GlobalResultDTO del(@RequestBody UserReqDTO reqDTO){
        return userService.del(reqDTO);
    }
    @FunDescriptionAnno(name = "绑定组织")
    @RequestMapping(value = "/bindingOrg", method = RequestMethod.POST)
    public GlobalResultDTO bindingOrg(@RequestBody UserReqDTO reqDTO){
        return userService.bindingOrg(reqDTO);
    }
}
