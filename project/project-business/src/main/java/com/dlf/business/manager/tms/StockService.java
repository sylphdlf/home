package com.dlf.business.manager.tms;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.tms.StockSearchDTO;

public interface StockService {

    GlobalResultDTO queryPageByParams(StockSearchDTO searchDTO);
}
