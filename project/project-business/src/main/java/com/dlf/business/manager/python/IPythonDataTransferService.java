package com.dlf.business.manager.python;

import com.dlf.model.dto.GlobalResultDTO;

/**
 * Created by Administrator on 2017/7/4.
 */
public interface IPythonDataTransferService {
    /**
     * job字符拆分
     * @param str
     * @return
     */
    GlobalResultDTO transferDataFromWordSplit(String str);

    /**
     * 天气
     * @param str
     * @return
     */
    GlobalResultDTO transferDataFromWeather(String str);
}
