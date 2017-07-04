package com.dlf.business.manager.python;

import com.dlf.model.dto.GlobalResultDTO;

/**
 * Created by Administrator on 2017/7/4.
 */
public interface IPythonDataTransferService {

    GlobalResultDTO transferDataFromWordSplit(String str);
}
