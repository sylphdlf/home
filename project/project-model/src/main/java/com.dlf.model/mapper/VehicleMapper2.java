package com.dlf.model.mapper;

import com.dlf.model.dto.tms.VehicleDTO;
import com.dlf.model.dto.tms.VehicleSearchDTO;
import com.dlf.model.po.Vehicle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VehicleMapper2 extends VehicleMapper{
    /**
     * 查询列表
     * @param searchDTO
     * @return
     */
    List<VehicleDTO> queryListByParams(VehicleSearchDTO searchDTO);

    /**
     * 软删除
     * @param vehicle
     * @return
     */
    int deleteSoft(Vehicle vehicle);
}