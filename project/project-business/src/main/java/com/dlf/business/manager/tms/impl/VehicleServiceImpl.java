package com.dlf.business.manager.tms.impl;

import com.dlf.business.manager.tms.ContactsService;
import com.dlf.business.manager.tms.VehicleService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.tms.*;
import com.dlf.model.enums.GlobalResultEnum;
import com.dlf.model.mapper.ContactsMapper2;
import com.dlf.model.mapper.VehicleMapper2;
import com.dlf.model.po.Contacts;
import com.dlf.model.po.Vehicle;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

    @Resource
    private VehicleMapper2 vehicleMapper;

    @Override
    public GlobalResultDTO queryPageByParams(VehicleSearchDTO searchDTO) {
        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        List<VehicleDTO> list = vehicleMapper.queryListByParams(searchDTO);
        PageInfo<VehicleDTO> pageInfo = new PageInfo<VehicleDTO>(list);
        return new GlobalResultDTO(pageInfo);
    }

    @Override
    public GlobalResultDTO add(VehicleReqDTO reqDTO) {
        //FIXME 验证
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(reqDTO, vehicle);
        try {
            int count = vehicleMapper.insert(vehicle);
            return count==1?GlobalResultDTO.SUCCESS():GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode(),GlobalResultEnum.FAIL.getMsg());
        }catch (Exception e){
            logger.error(e.getMessage());
            return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode(),GlobalResultEnum.FAIL.getMsg());
        }
    }

    @Override
    public GlobalResultDTO del(VehicleReqDTO reqDTO) {
        try {
            Vehicle vehicle = new Vehicle();
            BeanUtils.copyProperties(reqDTO, vehicle);
            int count = vehicleMapper.deleteSoft(vehicle);
            if(count == 1){
                return GlobalResultDTO.SUCCESS();
            }
            return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode(), GlobalResultEnum.FAIL.getMsg());
        }catch (Exception e){
            logger.error(e.getMessage());
            return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode(), GlobalResultEnum.FAIL.getMsg());
        }
    }

    @Override
    public GlobalResultDTO getVehicleRecord(VehicleReqDTO reqDTO) {
        try {
            if(null != reqDTO.getId()){
                Vehicle vehicle = vehicleMapper.selectByPrimaryKey(reqDTO.getId());
                VehicleResDTO resDTO = new VehicleResDTO();
                BeanUtils.copyProperties(vehicle, resDTO);
                return new GlobalResultDTO<VehicleResDTO>(resDTO);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode(), GlobalResultEnum.FAIL.getMsg());
    }
}
