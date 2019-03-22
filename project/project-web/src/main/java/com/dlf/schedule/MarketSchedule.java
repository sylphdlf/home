package com.dlf.schedule;

import com.dlf.business.manager.stock.StockMarketService;
import com.dlf.common.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class MarketSchedule {

    @Autowired
    StockMarketService stockMarketService;

    @Scheduled(cron = "0/3 0-59 9-14 * * ?")
    public void watching(){
//        11:30到13:00之间不执行
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();

        Calendar begin = Calendar.getInstance();
        begin.set(Calendar.HOUR_OF_DAY, 11);
        begin.set(Calendar.MINUTE, 30);
        begin.set(Calendar.SECOND, 0);
        Date timeBegin = begin.getTime();

        Calendar end = Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY, 13);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        Date timeEnd = end.getTime();
//
//        if(!(now.after(timeBegin) && now.before(timeEnd))){
//            stockMarketService.watching();
//        }
    }
    @Scheduled(cron = "1 1 1 * * ?")
    public void historyDel(){
        stockMarketService.historyDel();
    }
}
