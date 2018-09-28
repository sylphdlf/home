package com.dlf.business.manager.user.impl;

import com.dlf.business.anno.ExecuteTimeAnno;
import com.dlf.business.manager.redis.RedisService;
import com.dlf.business.manager.user.FunService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.*;
import com.dlf.model.enums.RedisPrefixEnums;
import com.dlf.model.enums.user.FunctionEnums;
import com.dlf.model.mapper.FunctionMapper2;
import com.dlf.model.po.Function;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FunServiceImpl implements FunService {
    @Value("${static.menu}")
    private String staticMenu;
    @Value("${static.source}")
    private String staticSource;
    @Resource
    private FunctionMapper2 functionMapper;
    @Resource
    private RedisService redisService;

    @Override
    public GlobalResultDTO getDefaultMenu() {
        System.out.println(staticMenu);
        return new GlobalResultDTO(staticMenu);
    }

    @Override
    public GlobalResultDTO getDefaultSource(){
        System.out.println(staticSource);
        return new GlobalResultDTO(staticSource);
    }

    @Override
    public GlobalResultDTO queryMenuByParams(FunSearchDTO searchDTO) {
        Function function = new Function();
        BeanUtils.copyProperties(searchDTO, function);
        function.setType(FunctionEnums.function_type_1.getCode());
        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        List<FunDTO> list = functionMapper.queryListByParams(function);
        PageInfo<FunDTO> pageInfo = new PageInfo<FunDTO>(list);
        return new GlobalResultDTO(pageInfo);
    }

    @Override
    public GlobalResultDTO queryFunByParams(FunSearchDTO searchDTO) {
        Function function = new Function();
        function.setParentCode(searchDTO.getCode());
        List<FunDTO> list = functionMapper.queryListByParams(function);
        return new GlobalResultDTO(list);
    }

    @Override
    @ExecuteTimeAnno
    public GlobalResultDTO getFunTree(FunReqDTO reqDTO) {
        List<TreeNode> treeList = null;
        //从缓存中获取数据
        treeList = (List<TreeNode>)redisService.getObj(RedisPrefixEnums.FUN_TREE_NODE.getCode());
        if(CollectionUtils.isEmpty(treeList)){
            List<TreeNode> funList = functionMapper.getTreeNodeByParams(reqDTO);
            //拼接树
            //模拟一个根节点
            TreeNode treeNode = new TreeNode();
            treeNode.setId(0L);
            treeNode.setCode("0");
            treeNode.setLabel("权限树");
            this.recursionNode(funList, treeNode, 4, 1);
            treeList = new ArrayList<TreeNode>(Collections.singletonList(treeNode));
            try {
                redisService.put(RedisPrefixEnums.FUN_TREE_NODE.getCode(), treeList);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        List<Long> selectedFunList = functionMapper.getCachedFunIdsByRoleId(reqDTO);
        FunResDTO funResDTO = new FunResDTO();
        funResDTO.setList(treeList);
        funResDTO.setOriginalIds(selectedFunList);
        return new GlobalResultDTO(funResDTO);
    }

    @Override
    public GlobalResultDTO add(FunReqDTO reqDTO) {
        Function fun = new Function();
        BeanUtils.copyProperties(reqDTO, fun);
        //parentCode + code
        fun.setCode(fun.getParentCode() + fun.getCode());
        int count = functionMapper.insert(fun);
        if(count == 1){
            return GlobalResultDTO.SUCCESS();
        }
        return GlobalResultDTO.FAIL("");
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
