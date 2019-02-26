package com.dlf.model.mapper;

import com.dlf.model.dto.stock.MarketInfoDTO;
import com.dlf.model.dto.stock.MarketInfoSearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MarketInfoMapper2 extends MarketInfoMapper{

    List<MarketInfoDTO> getNewestByParams(MarketInfoSearchDTO searchDTO);
}