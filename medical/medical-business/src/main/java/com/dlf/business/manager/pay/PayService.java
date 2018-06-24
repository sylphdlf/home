package com.dlf.business.manager.pay;

import java.io.InputStream;
import java.util.Map;

public interface PayService {
    /**
     * 保存回调日志
     * @param object
     */
    void saveNotifyLogs(Object object);
}
