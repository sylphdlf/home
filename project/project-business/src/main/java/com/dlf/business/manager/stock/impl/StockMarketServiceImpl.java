package com.dlf.business.manager.stock.impl;

import com.dlf.business.anno.RedisCacheAnno;
import com.dlf.business.manager.redis.RedisService;
import com.dlf.business.manager.stock.StockMarketService;
import com.dlf.common.utils.HttpUtils;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.stock.*;
import com.dlf.model.enums.IRedisPrefixEnums;
import com.dlf.model.enums.RedisPrefixEnums;
import com.dlf.model.enums.stock.GtimgEnums;
import com.dlf.model.mapper.MarketInfoMapper;
import com.dlf.model.mapper.MarketInfoMapper2;
import com.dlf.model.mapper.MarketMapper2;
import com.dlf.model.po.Market;
import com.dlf.model.po.MarketInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class StockMarketServiceImpl implements StockMarketService {

    @Resource
    private MarketMapper2 marketMapper;
    @Resource
    private MarketInfoMapper2 marketInfoMapper;
    @Autowired
    RedisService redisService;
    //判断数据重复的缓存时间
    private static final Long REPEAT_DATA_TIMEOUT = 10L;

    private static final String market_url = "http://qt.gtimg.cn/q=";

    @Override
    public GlobalResultDTO queryPageByParams(StockMarketSearchDTO searchDTO) {
        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        List<StockMarketDTO> list = marketMapper.queryListByParams(searchDTO);
        if(!CollectionUtils.isEmpty(list)){
            for(StockMarketDTO thisDTO: list){
                MarketInfo marketInfo = (MarketInfo)redisService.getObj(RedisPrefixEnums.MARKET_NEWEST.getCode() + thisDTO.getCode());
                if(null != marketInfo){
                    thisDTO.setPrice(marketInfo.getPrice());
                }
            }
        }
        PageInfo<StockMarketDTO> pageInfo = new PageInfo<StockMarketDTO>(list);
        return new GlobalResultDTO(pageInfo);
    }

    @Override
    public GlobalResultDTO add(StockMarketReqDTO reqDTO) {
        String returnStr = HttpUtils.httpGet(market_url + reqDTO.getMarket() + reqDTO.getCode());
        String dataStr = returnStr.split("=")[1];
        String[] dataList = dataStr.split("~");
        Market market = new Market();
        market.setCode(dataList[2]);
        market.setName(dataList[1]);
        market.setMarket(reqDTO.getMarket());
        market.setWatchingStatus(0);
        int count = marketMapper.insert(market);
        if(count == 1){
            return GlobalResultDTO.SUCCESS();
        }
        return GlobalResultDTO.FAIL();
    }

    @Override
    public GlobalResultDTO watching(StockMarketReqDTO reqDTO) {
        Market market = marketMapper.selectByPrimaryKey(reqDTO.getId());
        market.setWatchingStatus(reqDTO.getWatchingStatus());
        int count = marketMapper.updateByPrimaryKey(market);
        if(count == 1){
            //清除缓存
            redisService.delKey(IRedisPrefixEnums.market_list);
            return GlobalResultDTO.SUCCESS();
        }
        return GlobalResultDTO.FAIL();
    }

    @Override
    @Transactional
    public void watching() {
        List<StockMarketDTO> result = this.preWatching();
        if(!CollectionUtils.isEmpty(result)){
            for(StockMarketDTO thisDTO: result){
                String returnStr = HttpUtils.httpGet(market_url + thisDTO.getMarket() + thisDTO.getCode());
                String thisStr = returnStr.split("=")[1];
                String[] strList = thisStr.split("~");
                //去重, 根据数据更新时间
                String redisKey = strList[GtimgEnums.INDEX_2.getIndex()] + strList[GtimgEnums.INDEX_30.getIndex()];
                if(null != redisService.get(redisKey)){
                    continue;
                }else{
                    redisService.put(redisKey, redisKey, REPEAT_DATA_TIMEOUT);
                }
                MarketInfo marketInfo = new MarketInfo();
                marketInfo.setMarket(thisDTO.getMarket());
                GtimgEnums.setParams(marketInfo, strList);
                //直接扔redis里面
//                marketInfoMapper.insert(marketInfo);
                redisService.put(RedisPrefixEnums.MARKET_NEWEST.getCode() + marketInfo.getCode(), marketInfo);
            }
        }
    }
    @RedisCacheAnno(key = IRedisPrefixEnums.market_list, timeout = 1800)
    private List<StockMarketDTO> preWatching(){
        StockMarketSearchDTO searchDTO = new StockMarketSearchDTO();
        searchDTO.setWatchingStatus(1);
        return marketMapper.queryListByParams(searchDTO);
    }
    @Override
    public GlobalResultDTO showNewest(MarketInfoSearchDTO searchDTO) {
//        List<MarketInfoDTO> result = marketInfoMapper.getNewestByParams(searchDTO);
        MarketInfo marketInfo = (MarketInfo)redisService.getObj(RedisPrefixEnums.MARKET_NEWEST.getCode() + searchDTO.getCode());
        return new GlobalResultDTO(marketInfo);
    }

    @Override
    public GlobalResultDTO queryChartData(MarketInfoSearchDTO searchDTO) {
        //设置查询时间范围
        Calendar calStart = Calendar.getInstance();
        calStart.add(Calendar.HOUR_OF_DAY, -1);
        searchDTO.setRefreshTimeStart(calStart.getTime());
        List<MarketInfoDTO> result = marketInfoMapper.queryListByParams(searchDTO);
        if(!CollectionUtils.isEmpty(result)){
            //去重
            Set<Date> repeatCheck = new HashSet<>();
            Iterator<MarketInfoDTO> iterator = result.iterator();
            while (iterator.hasNext()){
                Date thisDate = iterator.next().getRefreshTime();
                if(repeatCheck.contains(thisDate)){
                    iterator.remove();
                    continue;
                }
                repeatCheck.add(thisDate);
            }
        }
        return new GlobalResultDTO(result);
    }

    @Override
    public void historyDel() {
        
    }

    public static void main(String[] args) {
        String returnStr = HttpUtils.httpGet("http://qt.gtimg.cn/q=sz002230");
        String thisStr = returnStr.split("=")[1];
        String[] strList = thisStr.split("~");

    }
}
