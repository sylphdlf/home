package com.dlf.business.manager.user.impl;

import com.dlf.business.exception.MyException;
import com.dlf.business.manager.user.OrgService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.enums.user.OrgResultEnum;
import com.dlf.model.dto.user.OrgReqDTO;
import com.dlf.model.dto.user.OrgResDTO;
import com.dlf.model.dto.user.TreeNode;
import com.dlf.model.mapper.OrganizationMapper2;
import com.dlf.model.po.Organization;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.reflect.generics.tree.Tree;

import javax.annotation.Resource;
import java.util.*;

@Service
public class OrgServiceImpl implements OrgService {

    @Resource
    private OrganizationMapper2 organizationMapper;

    @Override
    public GlobalResultDTO getOrgTree(OrgReqDTO reqDTO) {
        List<TreeNode> list = null;
        if(null == reqDTO.getId()){
            list = organizationMapper.getAllAsTreeNode();
        }
        if(!CollectionUtils.isEmpty(list)){
            //组装节点
            list = this.nodePackage(list);
            return new GlobalResultDTO(list.get(0));
        }else{
            //无节点数据
            return GlobalResultDTO.FAIL(OrgResultEnum.ORG_TREE_EMPTY.getCode(), OrgResultEnum.ORG_TREE_EMPTY.getMsg());
        }
    }

    @Override
    public GlobalResultDTO addOrgNode(OrgReqDTO reqDTO) {
        try {
            if(StringUtils.isBlank(reqDTO.getParentCode())){
                reqDTO.setParentCode("0");
            }
            Organization org = new Organization();
            BeanUtils.copyProperties(reqDTO, org);
            organizationMapper.insertWithIdReturn(org);
            //只展示自定义字段
            OrgResDTO resDTO = new OrgResDTO();
            BeanUtils.copyProperties(org, resDTO);
            return new GlobalResultDTO(resDTO);
        }catch (Exception e){
            return GlobalResultDTO.FAIL("");
        }
    }

    /**
     * 节点组装
     * @param list
     * @return
     */
    private List<TreeNode> nodePackage(List<TreeNode> list){
        //转为map，格式为<code,TreeNode>
        Map<String, TreeNode> thisMap = new HashMap<String, TreeNode>();
        TreeNode root = new TreeNode();
        for(TreeNode thisNode: list){
            //把parentCode=0的数据单独处理
            if("0".equals(thisNode.getParent())){
                 root = thisNode;
            }
            thisMap.put(thisNode.getCode(), thisNode);
        }
        return recursionNode(thisMap, root);
    }

    /**
     * 递归拼装节点
     * @param thisMap
     * @return
     */
    private List<TreeNode> recursionNode(Map<String, TreeNode> thisMap, TreeNode root){
        Set<Map.Entry<String, TreeNode>> thisEntry = thisMap.entrySet();
        for(Map.Entry<String, TreeNode> entry: thisEntry){
            TreeNode thisNode = entry.getValue();
            if(root.getCode().equals(thisNode.getParent())){

            }

        }
        return null;
    }
}
