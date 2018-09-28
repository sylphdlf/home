package com.dlf.business.manager.tms.impl;

import com.dlf.business.exception.MyException;
import com.dlf.business.manager.tms.PickupService;
import com.dlf.common.utils.CodeGenerateUtils;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.tms.*;
import com.dlf.model.enums.GlobalResultEnum;
import com.dlf.model.mapper.PickupDetailMapper2;
import com.dlf.model.mapper.PickupMapper2;
import com.dlf.model.po.Pickup;
import com.dlf.model.po.PickupDetail;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PickupServiceImpl implements PickupService {

    private Logger logger = LoggerFactory.getLogger(PickupService.class);

    @Resource
    private PickupMapper2 pickupMapper;
    @Resource
    private PickupDetailMapper2 pickupDetailMapper;

    @Override
    public GlobalResultDTO queryPageByParams(PickupSearchDTO searchDTO) {
        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        List<PickupDTO> list = pickupMapper.queryListByParams(searchDTO);
        PageInfo<PickupDTO> pageInfo = new PageInfo<PickupDTO>(list);
        return new GlobalResultDTO(pageInfo);
    }

    @Override
    @Transactional
    public GlobalResultDTO add(PickupReqDTO reqDTO) {
        try {
            if(!CollectionUtils.isEmpty(reqDTO.getList())){
                Pickup pickup = new Pickup();
                BeanUtils.copyProperties(reqDTO, pickup);
                pickup.setPickupCode(CodeGenerateUtils.codeGenerateByPrefix("P"));
                pickup.setCount(reqDTO.getList().size());
                int count = pickupMapper.insertWithIdReturn(pickup);
                if(count == 1){
                    List<PickupDetailReqDTO> list = reqDTO.getList();
                    for(PickupDetailReqDTO thisDTO : list){
                        PickupDetail detail = new PickupDetail();
                        detail.setPickupId(pickup.getId());
                        BeanUtils.copyProperties(thisDTO, detail);
                        pickupDetailMapper.insert(detail);
                    }
                }else{
                    throw new MyException(GlobalResultEnum.FAIL.getCode(),GlobalResultEnum.FAIL.getMsg());
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode(),GlobalResultEnum.FAIL.getMsg());
    }
}
