package com.dlf.business.manager.user.impl;

import com.dlf.business.manager.user.FunService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.FunDTO;
import com.dlf.model.dto.user.FunReqDTO;
import com.dlf.model.dto.user.FunSearchDTO;
import com.dlf.model.enums.user.FunctionEnum;
import com.dlf.model.mapper.FunctionMapper2;
import com.dlf.model.po.Function;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FunServiceImpl implements FunService {
    @Value("${static.menu}")
    private String staticMenu;
    @Value("${static.source}")
    private String staticSource;
    @Resource
    private FunctionMapper2 functionMapper;

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
        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        searchDTO.setType(FunctionEnum.function_type_1.getCode());
        List<FunDTO> list = functionMapper.queryListByParams(searchDTO);
        PageInfo<FunDTO> pageInfo = new PageInfo<FunDTO>(list);
        return new GlobalResultDTO(pageInfo);
    }

    @Override
    public GlobalResultDTO add(FunReqDTO reqDTO) {
        Function fun = new Function();
        BeanUtils.copyProperties(reqDTO, fun);
        int count = functionMapper.insert(fun);
        if(count == 1){
            return GlobalResultDTO.SUCCESS();
        }
        return GlobalResultDTO.FAIL("");
    }
}
