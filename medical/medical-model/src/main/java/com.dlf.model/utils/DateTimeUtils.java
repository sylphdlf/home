package com.dlf.model.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.util.resources.TimeZoneNames_zh_CN;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 时间工具
 * Date: 2017/8/22
 *
 * @author dailf@zendaimoney.com
 */
public class DateTimeUtils {

    protected static Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);

    public static final String HOURS_AND_MINUTES = "hh:mm";

    /**
     * 时间转换
     * @param timeStr
     * @return
     */
    public static Date transferStrToDate(String timeStr){
        Calendar cal = Calendar.getInstance();
        try {
            DateFormat df = new SimpleDateFormat(HOURS_AND_MINUTES);
            Date date = df.parse(timeStr);
            int year = cal.get(GregorianCalendar.YEAR);
            int month = cal.get(GregorianCalendar.MONTH);
            int day = cal.get(GregorianCalendar.DAY_OF_MONTH);
            cal.setTime(date);
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, day);
        }catch (ParseException e) {
            logger.error("时间转换出错");
        }
        return cal.getTime();
    }
}
