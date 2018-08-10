package com.dlf.web.controller.user;

import com.dlf.business.manager.user.RoleService;
import com.dlf.model.dto.GlobalResultDTO;
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
public class RoleController {

    @Autowired
    RoleService roleService;

    /**
     * 分页查询角色列表
     * @param searchDTO
     * @return
     */
    @RequestMapping(value = "/queryListByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryListByParams(@RequestBody RoleSearchDTO searchDTO) {
        return roleService.queryListByParams(searchDTO);
    }

    /**
     * 新增角色
     * @param reqDTO
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public GlobalResultDTO add(@RequestBody RoleReqDTO reqDTO){
        return roleService.add(reqDTO);
    }
}
