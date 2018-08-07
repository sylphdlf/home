package com.dlf.web.controller.user;

import com.dlf.business.manager.user.OrgService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.OrgReqDTO;
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
public class OrgController {

    @Autowired
    OrgService orgService;

    @RequestMapping(value = "/getOrgTreeRoot", method = RequestMethod.GET)
    public GlobalResultDTO getOrgTreeRoot(){
        return orgService.getOrgTreeRoot();
    }

    @RequestMapping(value = "/getOrgTreeLazy", method = RequestMethod.POST)
    public GlobalResultDTO getOrgTreeLazy(OrgReqDTO reqDTO){
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

    /**
     * 新增节点
     * @param reqDTO
     * @return
     */
    @RequestMapping(value = "/addOrgNode", method = RequestMethod.POST)
    public GlobalResultDTO addOrgNode(@RequestBody OrgReqDTO reqDTO){
        return orgService.addOrgNode(reqDTO);
    }
}
