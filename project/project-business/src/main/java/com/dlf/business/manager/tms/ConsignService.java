package com.dlf.business.manager.tms;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.tms.ConsignReqDTO;
import com.dlf.model.dto.tms.ConsignSearchDTO;

public interface ConsignService {
    /**
     * 新增（第一步）
     * @param reqDTO
     * @return
     */
//    GlobalResultDTO addStep1(ConsignReqDTO reqDTO);

    /**
     * 新增（第二步）
     * @param reqDTO
     * @return
     */
//    GlobalResultDTO addStep2(ConsignReqDTO reqDTO);

    /**
     * 获取托运单记录
     * @param reqDTO
     * @return
     */
    GlobalResultDTO getConsignRecord(ConsignReqDTO reqDTO);

    /**
     * 分页查询
     * @param searchDTO
     * @return
     */
    GlobalResultDTO queryPageByParams(ConsignSearchDTO searchDTO);
    /**
     * 分页查询(子表左连接)
     * @param searchDTO
     * @return
     */
    GlobalResultDTO queryDetailPageByParams(ConsignSearchDTO searchDTO);

    /**
     * 新增
     * @param reqDTO
     * @return
     */
    GlobalResultDTO add(ConsignReqDTO reqDTO);
}
