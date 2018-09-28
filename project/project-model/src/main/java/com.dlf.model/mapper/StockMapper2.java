package com.dlf.model.mapper;

import com.dlf.model.dto.tms.StockDTO;
import com.dlf.model.dto.tms.StockSearchDTO;
import com.dlf.model.po.Stock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockMapper2 extends StockMapper{

    List<StockDTO> queryListByParams(StockSearchDTO searchDTO);
}