package com.dlf.model.mapper;

import com.dlf.model.dto.user.RoleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper2 extends RoleMapper{
    /**
     * 根据用户ID获取角色列表
     * @param id
     * @return
     */
    List<RoleDTO> getRoleListByUser(Long id);
}
