package com.dlf.business.manager.pay.impl;

import com.dlf.business.manager.pay.PayService;
import com.dlf.common.utils.PayUtils;
import com.dlf.model.mapper.PayNotifyLogMapper;
import com.dlf.model.po.PayNotifyLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Map;

@Service
public class PayServiceImpl implements PayService {

    private Log logger = LogFactory.getLog(PayServiceImpl.class);

    @Resource
    PayNotifyLogMapper payNotifyLogMapper;

    @Async
    public void saveNotifyLogs(Object obj) {
        try {
            PayNotifyLog payNotifyLog = new PayNotifyLog();
            payNotifyLog.setChannel((byte)1);
            if(obj instanceof Map){
                payNotifyLog.setContent(PayUtils.getParams((Map<String, String[]>)obj));
            }else if(obj instanceof InputStream){
                payNotifyLog.setContent(PayUtils.getJsonParams((InputStream)obj));
            }
            payNotifyLogMapper.insert(payNotifyLog);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

}
