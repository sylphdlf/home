package com.dlf.web.controller.stock;

import com.dlf.business.anno.FunDescriptionAnno;
import com.dlf.business.anno.MenuDescriptionAnno;
import com.dlf.business.manager.stock.StockMarketService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.stock.MarketInfoSearchDTO;
import com.dlf.model.dto.stock.StockMarketReqDTO;
import com.dlf.model.dto.stock.StockMarketSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监控
 */
@RestController
@RequestMapping("/stockMarket")
@MenuDescriptionAnno(name = "行情", parent = "监控")
public class StockMarketController {

    @Autowired
    StockMarketService stockMarketService;

    @FunDescriptionAnno(name="查询")
    @RequestMapping(value = "/queryPageByParams", method = RequestMethod.POST)
    public GlobalResultDTO queryPageByParams(@RequestBody StockMarketSearchDTO searchDTO) {
        return stockMarketService.queryPageByParams(searchDTO);
    }

    @FunDescriptionAnno(name="新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public GlobalResultDTO add(@RequestBody StockMarketReqDTO reqDTO) {
        return stockMarketService.add(reqDTO);
    }

    @FunDescriptionAnno(name="开始监控")
    @RequestMapping(value = "/startWatching", method = RequestMethod.POST)
    public GlobalResultDTO startWatching(@RequestBody StockMarketReqDTO reqDTO) {
        return stockMarketService.watching(reqDTO);
    }

    @FunDescriptionAnno(name="停止监控")
    @RequestMapping(value = "/stopWatching", method = RequestMethod.POST)
    public GlobalResultDTO stopWatching(@RequestBody StockMarketReqDTO reqDTO) {
        return stockMarketService.watching(reqDTO);
    }

    @FunDescriptionAnno(name="最新数据")
    @RequestMapping(value = "/showNewest", method = RequestMethod.POST)
    public GlobalResultDTO showNewest(@RequestBody MarketInfoSearchDTO searchDTO){
        return stockMarketService.showNewest(searchDTO);
    }

    @FunDescriptionAnno(name="图表数据")
    @RequestMapping(value = "/queryChartData", method = RequestMethod.POST)
    public GlobalResultDTO queryChartData(@RequestBody MarketInfoSearchDTO searchDTO){
        return stockMarketService.queryChartData(searchDTO);
    }
}
