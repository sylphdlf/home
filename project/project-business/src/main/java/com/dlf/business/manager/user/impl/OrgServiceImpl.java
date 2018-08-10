package com.dlf.business.manager.user.impl;

import com.dlf.business.anno.ExecuteTimeAnno;
import com.dlf.business.manager.redis.RedisService;
import com.dlf.business.manager.user.OrgService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.enums.RedisPrefixEnums;
import com.dlf.model.enums.user.OrgResultEnum;
import com.dlf.model.dto.user.OrgReqDTO;
import com.dlf.model.dto.user.OrgResDTO;
import com.dlf.model.dto.user.TreeNode;
import com.dlf.model.mapper.OrganizationMapper2;
import com.dlf.model.po.Organization;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class OrgServiceImpl implements OrgService {

    @Resource
    private OrganizationMapper2 organizationMapper;
    @Resource
    private RedisService redisService;
    //递归层级
    @Value("${org.tree.recursion.level}")
    private int recursionLevel = 2;

    private void insert(Organization org){
        organizationMapper.insert(org);
    }
    @Override
    public GlobalResultDTO addOrgNode(OrgReqDTO reqDTO) {
//        for(int i=1;i<20;i++){
//            Organization org = new Organization();
//            org.setParentCode("1");
//            org.setCode("1" + i);
//            org.setName("child" + i);
//            this.insert(org);
//            for(int j=1;j<20;j++){
//                org.setParentCode("1" + i);
//                org.setCode("1" + i + j + "");
//                org.setName("child" + i + j);
//                this.insert(org);
//                for(int k = 1;k<20;k++){
//                    org.setParentCode("1" + i + j + "");
//                    org.setCode("1" + i + j + k + "");
//                    org.setName("child" + i + j + k);
//                    this.insert(org);
//                }
//            }
//        }
//        return GlobalResultDTO.SUCCESS();
        try {
            redisService.removeKey(RedisPrefixEnums.ORG_TREE_NODE.getCode());
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

    @Override
    public GlobalResultDTO getOrgTreeRoot() {
        OrgReqDTO reqDTO = new OrgReqDTO();
        reqDTO.setParentCode("0");
        return getOrgTree(reqDTO);
    }

    @Override
    public GlobalResultDTO getOrgTreeLazy(OrgReqDTO reqDTO) {
        return getOrgTree(reqDTO);
    }

    @Override
    @ExecuteTimeAnno
    public GlobalResultDTO getOrgTree(OrgReqDTO reqDTO) {
        //从缓存中拿数据
        List<TreeNode> list = null;
        List<TreeNode> treeNode = null;
        if(StringUtils.isBlank(reqDTO.getParentCode())){
            treeNode = (List<TreeNode>)redisService.getObj(RedisPrefixEnums.ORG_TREE_NODE.getCode());
            if(!CollectionUtils.isEmpty(treeNode)){
                return new GlobalResultDTO(treeNode);
            }
            long start = System.currentTimeMillis();
            list = organizationMapper.getAllAsTreeNode();
            long end = System.currentTimeMillis();
            System.out.println("查库时间：" + (end - start));
        }else{
            Organization organization = new Organization();
            BeanUtils.copyProperties(reqDTO, organization);
            list = organizationMapper.getTreeNodeByParams(organization);
            return new GlobalResultDTO(list);
        }
        if(!CollectionUtils.isEmpty(list)){
            //组装节点
            treeNode = this.nodePackage(list);
            //放到缓存中
            redisService.put(RedisPrefixEnums.ORG_TREE_NODE.getCode(),treeNode);
            return new GlobalResultDTO(treeNode);
        }else{
            //无节点数据
            return GlobalResultDTO.FAIL(OrgResultEnum.ORG_TREE_EMPTY.getCode(), OrgResultEnum.ORG_TREE_EMPTY.getMsg());
        }
    }
    /**
     * 节点组装
     * @param list
     * @return
     */
    @ExecuteTimeAnno
    private List<TreeNode> nodePackage(List<TreeNode> list){
        //取出根节点
        TreeNode root = null;
        for(TreeNode thisNode: list){
            if(thisNode.getParent().equals("0")){
                root = thisNode;
            }
        }
        return new ArrayList<TreeNode>(Collections.singletonList(recursionNode(list, root, recursionLevel, 1)));
    }

    /**
     * 递归拼装节点
     * @param list 节点集合
     * @param node 节点
     * @param level 递归层级，节点太深会导致页面崩溃
     * @param count 每进一层，count++, 初始为1
     * @return
     */
    public TreeNode recursionNode(List<TreeNode> list, TreeNode node, final int level, int count){
        //递归层级=level则返回
        if(count == level){
            return node;
        }
        for (TreeNode thisNode : list) {
            if (node.getCode().equals(thisNode.getParent())) {
                if (CollectionUtils.isEmpty(node.getChildren())) {
                    List<TreeNode> children = new ArrayList<TreeNode>();
                    children.add(thisNode);
                    node.setChildren(children);
                } else {
                    node.getChildren().add(thisNode);
                }
                int newCount = count + 1;
                recursionNode(list, thisNode, level, newCount);
            }
        }
        return node;
    }
}
