package com.dlf.model.mapper;

import com.dlf.model.dto.user.TreeNode;
import com.dlf.model.po.Organization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizationMapper2 extends OrganizationMapper{
    /**
     * 获取所有数据
     * @return
     */
    List<Organization> getAll();

    /**
     * 查询树
     * @return
     */
    List<TreeNode> getAllAsTreeNode();

    /**
     * 新增并返回id
     * @param organization
     * @return
     */
    long insertWithIdReturn(Organization organization);
}
