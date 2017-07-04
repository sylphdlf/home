package com.dlf.user.business.manager.python;

import com.dlf.user.model.dto.GlobalResultDTO;

/**
 * Created by Administrator on 2017/7/4.
 */
public interface IPythonDataTransferService {

    GlobalResultDTO transferDataFromWordSplit(String str);
}
