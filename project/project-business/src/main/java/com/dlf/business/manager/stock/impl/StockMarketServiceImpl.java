package com.dlf.business.manager.stock.impl;

import com.dlf.business.anno.RedisCacheAnno;
import com.dlf.business.manager.redis.RedisService;
import com.dlf.business.manager.stock.StockMarketService;
import com.dlf.common.utils.BeanUtils;
import com.dlf.common.utils.HttpUtils;
import com.dlf.common.utils.market.MarketUtils;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.stock.*;
import com.dlf.model.enums.IRedisPrefixEnums;
import com.dlf.model.enums.RedisPrefixEnums;
import com.dlf.model.enums.stock.GtimgEnums;
import com.dlf.model.enums.stock.MarketEnums;
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
import java.math.BigDecimal;
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
                String[] strList = MarketUtils.queryMarketInfo(market_url + thisDTO.getMarket() + thisDTO.getCode());
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

    @Override
    @Transactional
    public GlobalResultDTO scanMarket(StockMarketSearchDTO searchDTO) {
        String count = redisService.get(RedisPrefixEnums.SCAN_MARKET.getCode());
        if(null != count && !count.equals("0")){
            return GlobalResultDTO.FAIL("任务正在执行，请稍后再试，目前进度 " + count);
        }
        searchDTO.setWatchingStatus(MarketEnums.WATCHING_STATUS_1.getCode());
        //查出所有的数据
        List<StockMarketDTO> dtoList = marketMapper.queryListByParams(searchDTO);
        if(!CollectionUtils.isEmpty(dtoList)){
            new Thread(() -> {

                    StockMarketDTO thisDTO;
                    MarketInfo marketInfo = new MarketInfo();
                    for(int i=0;i<dtoList.size();i++){
                        try {
                            thisDTO = dtoList.get(i);
                            String[] strings = MarketUtils.queryMarketInfo(GtimgEnums.MARKET_URL.getName() + thisDTO.getMarket() + thisDTO.getCode());
                            GtimgEnums.setParams(marketInfo, strings);
                            //价格
                            thisDTO.setPrice(marketInfo.getPrice());
                            //涨跌
                            thisDTO.setRiseFall(marketInfo.getRiseFall());
                            //涨跌幅
                            thisDTO.setRiseFallRatio(marketInfo.getRiseFallRatio());

                            //目前是涨跌
                            int currentType = marketInfo.getRiseFallRatio().compareTo(BigDecimal.ZERO) >= 0 ? 1 : 2;
                            //上次涨跌
                            if(null != thisDTO.getType()){
                                int historyType = thisDTO.getType();
                                //设置连涨，连跌天数
                                if(historyType == currentType){
                                    thisDTO.setDays(thisDTO.getDays() + 1);
                                    //涨跌幅历史记录
                                    thisDTO.setRiseFallHistory(thisDTO.getRiseFallHistory() + "/" + marketInfo.getRiseFallRatio());
                                }else{
                                    thisDTO.setDays(1);
                                    //涨跌幅历史记录
                                    thisDTO.setRiseFallHistory(marketInfo.getRiseFallRatio() + "");
                                }
                            }else{
                                thisDTO.setDays(1);
                            }
                            thisDTO.setType(currentType);
                            Market market = new Market();
                            BeanUtils.copyProperties(thisDTO, market);
                            marketMapper.updateByPrimaryKey(market);
                            //放入缓存
                            if(i == dtoList.size()-1){
                                redisService.put(RedisPrefixEnums.SCAN_MARKET.getCode(), "0");
                            }else{
                                redisService.put(RedisPrefixEnums.SCAN_MARKET.getCode(), i + "/" + dtoList.size());
                            }
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
            }).start();
        }
        return GlobalResultDTO.SUCCESS("执行成功，再次点击查询进度");
    }

}
