package com.dlf.model.mapper;

import com.dlf.model.dto.user.FunDTO;
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
}