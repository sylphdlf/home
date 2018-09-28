package com.dlf.business.manager.tms;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.tms.VehicleReqDTO;
import com.dlf.model.dto.tms.VehicleSearchDTO;

public interface VehicleService {
    /**
     * 分页查询
     * @param searchDTO
     * @return
     */
    GlobalResultDTO queryPageByParams(VehicleSearchDTO searchDTO);

    /**
     * 新增
     * @param reqDTO
     * @return
     */
    GlobalResultDTO add(VehicleReqDTO reqDTO);

    /**
     * 删除
     * @param reqDTO
     * @return
     */
    GlobalResultDTO del(VehicleReqDTO reqDTO);

    /**
     * 获取车辆信息
     * @param reqDTO
     * @return
     */
    GlobalResultDTO getVehicleRecord(VehicleReqDTO reqDTO);
}
