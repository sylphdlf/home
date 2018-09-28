package com.dlf.model.mapper;

import com.dlf.model.dto.tms.ConsignDTO;
import com.dlf.model.dto.tms.PickupDTO;
import com.dlf.model.dto.tms.PickupSearchDTO;
import com.dlf.model.po.Pickup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PickupMapper2 extends PickupMapper{

    List<PickupDTO> queryListByParams(PickupSearchDTO searchDTO);

    int insertWithIdReturn(Pickup pickup);
}