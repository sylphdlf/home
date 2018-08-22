package com.dlf.business.manager.user.impl;

import com.dlf.business.exception.MyException;
import com.dlf.business.factory.OrgRolesFactory;
import com.dlf.business.manager.user.RoleService;
import com.dlf.common.utils.CompareUtils;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.enums.user.RoleResultEnum;
import com.dlf.model.dto.user.*;
import com.dlf.model.mapper.RoleMapper2;
import com.dlf.model.po.Role;
import com.dlf.model.po.Role2;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper2 roleMapper;

    @Override
    public GlobalResultDTO queryListByParams(RoleSearchDTO searchDTO) {
        try {
            PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
            List<RoleDTO> list = roleMapper.queryListByParams(searchDTO);
            PageInfo<RoleDTO> pageInfo = new PageInfo<RoleDTO>(list);
            return new GlobalResultDTO(pageInfo);
        }catch (Exception e){
            return GlobalResultDTO.FAIL(e.getMessage());
        }
    }

    @Override
    public GlobalResultDTO add(RoleReqDTO reqDTO) {
        try {
            if(StringUtils.isBlank(reqDTO.getName())){
                throw new MyException(RoleResultEnum.ROLE_REQ_NAME_NULL.getCode(), RoleResultEnum.ROLE_REQ_NAME_NULL.getMsg());
            }
            if(StringUtils.isBlank(reqDTO.getCode())){
                throw new MyException(RoleResultEnum.ROLE_REQ_CODE_NULL.getCode(), RoleResultEnum.ROLE_REQ_CODE_NULL.getMsg());
            }
            Role role = new Role();
            BeanUtils.copyProperties(reqDTO, role);
            int count = roleMapper.insert(role);
            if(count == 1){
                return GlobalResultDTO.SUCCESS();
            }
            return GlobalResultDTO.FAIL("");
        }catch (MyException e){
            return GlobalResultDTO.FAIL(e.getMessage());
        }
    }

    @Override
    public GlobalResultDTO bindingFun(RoleReqDTO reqDTO) {
        List<Long> originalIds = reqDTO.getOriginalIds();
        List<Long> targetIds = reqDTO.getTargetIds();
        //待新增的id
        List<Long> toAddIds = new ArrayList<Long>();
        //待删除的id
        List<Long> toDelIds = new ArrayList<Long>();
        CompareUtils.getAddAndDel(originalIds, targetIds, toAddIds, toDelIds);
        //操作数据库
        Role2 role2 = null;
        if(!CollectionUtils.isEmpty(toAddIds)){
            role2 = new Role2();
            role2.setId(reqDTO.getId());
            role2.setToAddIds(toAddIds);
            roleMapper.insertRoleFuns(role2);
        }
        if(!CollectionUtils.isEmpty(toDelIds)){
            role2 = new Role2();
            role2.setId(reqDTO.getId());
            role2.setToDelIds(toDelIds);
            roleMapper.delRoleFuns(role2);
        }
        //移除factory中缓存的数据
        OrgRolesFactory.removeOrgId(reqDTO.getId());
        return GlobalResultDTO.SUCCESS();
    }
}
