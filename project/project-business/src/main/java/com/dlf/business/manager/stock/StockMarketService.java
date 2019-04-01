package com.dlf.business.manager.stock;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.stock.MarketInfoSearchDTO;
import com.dlf.model.dto.stock.StockMarketReqDTO;
import com.dlf.model.dto.stock.StockMarketSearchDTO;

public interface StockMarketService {

    GlobalResultDTO queryPageByParams(StockMarketSearchDTO searchDTO);

    GlobalResultDTO add(StockMarketReqDTO reqDTO);

    GlobalResultDTO watching(StockMarketReqDTO reqDTO);

    void watching();

    GlobalResultDTO showNewest(MarketInfoSearchDTO searchDTO);

    GlobalResultDTO queryChartData(MarketInfoSearchDTO searchDTO);

    void historyDel();

    GlobalResultDTO scanMarket(StockMarketSearchDTO searchDTO);
}
