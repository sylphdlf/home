package com.dlf.web.controller.user;

import com.dlf.business.anno.FunDescriptionAnno;
import com.dlf.business.anno.MenuDescriptionAnno;
import com.dlf.business.manager.user.OrgService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.OrgReqDTO;
import com.dlf.model.dto.user.OrgSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 */
@RestController
@RequestMapping("/org")
@MenuDescriptionAnno(name = "组织机构管理", parent = "用户管理")
public class OrgController {

    @Autowired
    OrgService orgService;

    @RequestMapping(value = "/getOrgTreeRoot", method = RequestMethod.GET)
    public GlobalResultDTO getOrgTreeRoot(){
        return orgService.getOrgTreeRoot();
    }

    @FunDescriptionAnno(name = "懒加载组织机构树")
    @RequestMapping(value = "/getOrgTreeLazy", method = RequestMethod.POST)
    public GlobalResultDTO getOrgTreeLazy(@RequestBody OrgReqDTO reqDTO){
        return orgService.getOrgTreeLazy(reqDTO);
    }
    /**
     * 获取组织机构树
     * @return
     */
    @RequestMapping(value = "/getOrgTree",method = RequestMethod.POST)
    public GlobalResultDTO getOrgTree(@RequestBody OrgReqDTO reqDTO) {
        return orgService.getOrgTree(reqDTO);
    }

    @FunDescriptionAnno(name = "新增组织机构")
    @RequestMapping(value = "/addOrgNode", method = RequestMethod.POST)
    public GlobalResultDTO addOrgNode(@RequestBody OrgReqDTO reqDTO){
        return orgService.addOrgNode(reqDTO);
    }

    @FunDescriptionAnno(name = "获取组织机构下的角色")
    @RequestMapping(value = "/getRolePageByOrg", method = RequestMethod.POST)
    public GlobalResultDTO getRolePageByOrg(@RequestBody OrgSearchDTO searchDTO){
        return orgService.getRolePageByOrg(searchDTO);
    }

    @FunDescriptionAnno(name = "组织绑定角色")
    @RequestMapping(value = "/bindingRole", method = RequestMethod.POST)
    public GlobalResultDTO bindingRole(@RequestBody OrgReqDTO reqDTO){
        return orgService.bindingRole(reqDTO);
    }
}
