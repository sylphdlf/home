package com.dlf.business.manager.tms.impl;

import com.dlf.business.manager.tms.StockService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.tms.ConsignDTO;
import com.dlf.model.dto.tms.StockDTO;
import com.dlf.model.dto.tms.StockSearchDTO;
import com.dlf.model.mapper.StockMapper2;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    @Resource
    private StockMapper2 stockMapper;

    @Override
    public GlobalResultDTO queryPageByParams(StockSearchDTO searchDTO) {
        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        List<StockDTO> list = stockMapper.queryListByParams(searchDTO);
        PageInfo<StockDTO> pageInfo = new PageInfo<StockDTO>(list);
        return new GlobalResultDTO(pageInfo);
    }
}
