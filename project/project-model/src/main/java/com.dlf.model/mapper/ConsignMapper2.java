package com.dlf.model.mapper;

import com.dlf.model.dto.tms.ConsignDTO;
import com.dlf.model.dto.tms.ConsignSearchDTO;
import com.dlf.model.po.Consign;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsignMapper2 extends ConsignMapper{

    int insertWithIdReturn(Consign consign);

    Consign selectByPrimaryKeyByUser(@Param("id")Long id);

    List<ConsignDTO> queryDetailListByParams(ConsignSearchDTO searchDTO);
}