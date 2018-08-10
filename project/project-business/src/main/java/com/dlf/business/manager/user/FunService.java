package com.dlf.business.manager.user;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.FunReqDTO;
import com.dlf.model.dto.user.FunSearchDTO;

public interface FunService {

    /**
     * 新增
     * @param reqDTO
     * @return
     */
    GlobalResultDTO add(FunReqDTO reqDTO);

    /**
     * 获取可以配置的所有菜单,用于权限菜单中的级联下拉框
     * @return
     */
    GlobalResultDTO getDefaultMenu();

    /**
     * 获取可以配置的所有资源，用户权限菜单中的级联下拉框
     * @return
     */
    GlobalResultDTO getDefaultSource();

    /**
     * 分页查询菜单
     * @param searchDTO
     * @return
     */
    GlobalResultDTO queryMenuByParams(FunSearchDTO searchDTO);
}
