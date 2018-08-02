package com.dlf.business.manager.user.impl;

import com.dlf.business.exception.MyException;
import com.dlf.business.manager.user.OrgService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.enums.user.OrgResultEnum;
import com.dlf.model.dto.user.OrgReqDTO;
import com.dlf.model.mapper.OrganizationMapper2;
import com.dlf.model.po.Organization;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrgServiceImpl implements OrgService {

    @Resource
    private OrganizationMapper2 organizationMapper;

    @Override
    public GlobalResultDTO getOrgTree(OrgReqDTO reqDTO) {
        List<Organization> list = null;
        if(null == reqDTO.getId()){
            list = organizationMapper.getAll();
        }
        if(!CollectionUtils.isEmpty(list)){
            //组装节点

        }else{
            //无节点数据
            return GlobalResultDTO.FAIL(OrgResultEnum.ORG_TREE_EMPTY.getCode(), OrgResultEnum.ORG_TREE_EMPTY.getMsg());
        }
        return GlobalResultDTO.SUCCESS();
    }

}
