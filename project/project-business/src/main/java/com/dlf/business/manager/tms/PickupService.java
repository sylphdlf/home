package com.dlf.business.manager.tms;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.tms.PickupReqDTO;
import com.dlf.model.dto.tms.PickupSearchDTO;

public interface PickupService {

    GlobalResultDTO queryPageByParams(PickupSearchDTO searchDTO);

    GlobalResultDTO add(PickupReqDTO reqDTO);
}
