package com.dlf.model.mapper;

import com.dlf.model.dto.user.RoleDTO;
import com.dlf.model.dto.user.RoleReqDTO;
import com.dlf.model.po.Role;
import com.dlf.model.po.Role2;
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

    /**
     * 新增权限
     * @param role2
     */
    void insertRoleFuns(Role2 role2);

    /**
     * 删除权限
     * @param role2
     */
    void delRoleFuns(Role2 role2);

    int delRoleByUserId(Long id);

    void insertWithIdReturn(Role role);
}
