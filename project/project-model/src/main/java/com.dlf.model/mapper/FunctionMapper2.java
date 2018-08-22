package com.dlf.model.mapper;

import com.dlf.model.dto.user.FunDTO;
import com.dlf.model.dto.user.FunReqDTO;
import com.dlf.model.dto.user.TreeNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FunctionMapper2 extends FunctionMapper{
    /**
     * 通过userId获取资源列表
     * @param id
     * @return
     */
    List<FunDTO> getSourceListByUser(Long id);

    /**
     * 获取树节点列表
     * @param reqDTO
     * @return
     */
    List<TreeNode> getTreeNodeByParams(FunReqDTO reqDTO);

    /**
     * 获取角色关联的权限ID
     * @param reqDTO
     * @return
     */
    List<Long> getCachedFunIdsByRoleId(FunReqDTO reqDTO);
}