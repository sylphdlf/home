package com.dlf.business.manager.user.impl;

import com.dlf.business.exception.MyException;
import com.dlf.business.manager.user.RoleService;
import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.enums.GlobalResultEnum;
import com.dlf.model.dto.enums.RedisPrefixEnums;
import com.dlf.model.dto.enums.user.RoleResultEnum;
import com.dlf.model.dto.enums.user.UserResultEnum;
import com.dlf.model.dto.user.*;
import com.dlf.model.mapper.RoleMapper2;
import com.dlf.model.mapper.UserMapper2;
import com.dlf.model.po.Role;
import com.dlf.model.po.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
