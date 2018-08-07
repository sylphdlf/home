package com.dlf.business.manager.user;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.RoleReqDTO;
import com.dlf.model.dto.user.RoleSearchDTO;

public interface RoleService {

    /**
     * 查询角色列表
     * @param searchDTO
     * @return
     */
    GlobalResultDTO queryListByParams(RoleSearchDTO searchDTO);

    /**
     * 新增
     * @param reqDTO
     * @return
     */
    GlobalResultDTO add(RoleReqDTO reqDTO);
}
