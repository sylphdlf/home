package com.dlf.business.manager.tms.impl;

import com.dlf.business.exception.MyException;
import com.dlf.business.manager.tms.ConsignService;
import com.dlf.business.manager.tms.ContactsService;
import com.dlf.business.manager.tms.VehicleService;
import com.dlf.common.utils.BeanUtils;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.tms.*;
import com.dlf.model.enums.GlobalResultEnum;
import com.dlf.model.enums.tms.ConsignEnums;
import com.dlf.model.mapper.ConsignDetailMapper2;
import com.dlf.model.mapper.ConsignMapper2;
import com.dlf.model.mapper.ProductMapper2;
import com.dlf.model.mapper.StockMapper2;
import com.dlf.model.po.Consign;
import com.dlf.model.po.Product;
import com.dlf.model.po.Stock;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.assertj.core.util.diff.myers.MyersDiff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConsignServiceImpl implements ConsignService {

    private Logger logger = LoggerFactory.getLogger(ContactsServiceImpl.class);

    @Autowired
    ContactsService contactsService;
    @Autowired
    VehicleService vehicleService;

    @Resource
    private ConsignMapper2 consignMapper;
    @Resource
    private ProductMapper2 productMapper;
    @Resource
    private StockMapper2 stockMapper;
//    @Resource
//    private ConsignDetailMapper2 consignDetailMapper;

//    @Override
//    public GlobalResultDTO addStep1(ConsignReqDTO reqDTO) {
//        try {
//            if(null == reqDTO.getId()){
//                Consign consign = new Consign();
//                //FIXME 验证
//                BeanUtils.copyProperties(reqDTO, consign);
//                consignMapper.insertWithIdReturn(consign);
//                ConsignResDTO resDTO = new ConsignResDTO();
//                resDTO.setId(consign.getId());
//                return new GlobalResultDTO<ConsignResDTO>(resDTO);
//            }else{
//                Consign consign = consignMapper.selectByPrimaryKey(reqDTO.getId());
//                if(null != consign){
//                    BeanUtils.copyProperties(reqDTO, consign);
//                    consignMapper.updateByPrimaryKey(consign);
//                }
//                ConsignResDTO resDTO = new ConsignResDTO();
//                resDTO.setId(reqDTO.getId());
//                return new GlobalResultDTO<ConsignResDTO>(resDTO);
//            }
//        }catch (Exception e){
//            logger.error(e.getMessage());
//            return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode(), GlobalResultEnum.FAIL.getMsg());
//        }
//    }

//    @Override
//    @Transactional
//    public GlobalResultDTO addStep2(ConsignReqDTO reqDTO) {
//        try {
//            //先获取数据
//            Consign consign = consignMapper.selectByPrimaryKey(reqDTO.getId());
//            BeanUtils.copyPropertiesIgnoreNull(reqDTO, consign);
//            //设置状态，更新主表
//            consign.setStatus(ConsignEnums.CONSIGN_STATUS_1.getCode());
//            consignMapper.updateByPrimaryKey(consign);
//            //新增子表数据
//            if(!CollectionUtils.isEmpty(reqDTO.getProductList())){
//                for(ProductVO thisVO : reqDTO.getProductList()){
//                    ConsignDetail consignDetail = new ConsignDetail();
//                    BeanUtils.copyProperties(thisVO, consignDetail);
//                    consignDetail.setConsignId(consign.getId());
//                    if(null == thisVO.getId()){
//                        consignDetailMapper.insert(consignDetail);
//                    }else{
//                        consignDetailMapper.updateByPrimaryKey(consignDetail);
//                    }
//                }
//            }
//            return GlobalResultDTO.SUCCESS();
//        }catch (Exception e){
//            logger.error(e.getMessage());
//            return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode(), GlobalResultEnum.FAIL.getMsg());
//        }
//    }

    @Override
    public GlobalResultDTO getConsignRecord(ConsignReqDTO reqDTO) {
        try {
            if(null != reqDTO.getId()){
                Consign consign = consignMapper.selectByPrimaryKey(reqDTO.getId());
                ConsignDTO consignDTO = new ConsignDTO();
                BeanUtils.copyProperties(consign, consignDTO);
                //设置 发货人，收货人，司机
                this.setConsignData(consignDTO);
                return new GlobalResultDTO<ConsignDTO>(consignDTO);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode(),GlobalResultEnum.FAIL.getMsg());
    }

    @Override
    public GlobalResultDTO queryPageByParams(ConsignSearchDTO searchDTO) {
        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        List<ConsignDTO> list = consignMapper.queryListByParams(searchDTO);
        PageInfo<ConsignDTO> pageInfo = new PageInfo<ConsignDTO>(list);
        return new GlobalResultDTO(pageInfo);
    }

    @Override
    public GlobalResultDTO queryDetailPageByParams(ConsignSearchDTO searchDTO) {
        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        List<ConsignDTO> list = consignMapper.queryDetailListByParams(searchDTO);
        PageInfo<ConsignDTO> pageInfo = new PageInfo<ConsignDTO>(list);
        return new GlobalResultDTO(pageInfo);
    }

    @Override
    @Transactional
    public GlobalResultDTO add(ConsignReqDTO reqDTO) {
        try {
            //新增商品
            this.addProduct(reqDTO);
            //新增库存
            this.addStock(reqDTO);
            Consign consign = new Consign();
            BeanUtils.copyProperties(reqDTO, consign);
            consign.setStatus(ConsignEnums.CONSIGN_STATUS_1.getCode());
            int count = consignMapper.insert(consign);
            if(count == 1){
                return GlobalResultDTO.SUCCESS();
            }
            throw new MyException(GlobalResultEnum.FAIL.getCode(), GlobalResultEnum.FAIL.getMsg());
        }catch (Exception e){
            logger.error(e.getMessage());
            return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode(), GlobalResultEnum.FAIL.getMsg());
        }
    }

    /**
     * 新增商品
     * @param reqDTO
     * @throws Exception
     */
    private void addProduct(ConsignReqDTO reqDTO) throws Exception{
        //新增商品并返回ID
        ProductReqDTO productReqDTO = new ProductReqDTO();
        productReqDTO.setProductFromConsign(reqDTO);
        Product product = new Product();
        BeanUtils.copyProperties(productReqDTO, product);
        int count = productMapper.insertWithIdReturn(product);
        if(count == 1){
            reqDTO.setProductId(product.getId());
        }else{
            throw new MyException(GlobalResultEnum.FAIL.getCode(), GlobalResultEnum.FAIL.getMsg());
        }
    }

    /**
     * 新增库存
     * @param reqDTO
     * @throws Exception
     */
    private void addStock(ConsignReqDTO reqDTO) throws Exception{
        StockReqDTO stockReqDTO = new StockReqDTO();
        stockReqDTO.setStockFromConsign(reqDTO);
        Stock stock = new Stock();
        BeanUtils.copyProperties(stockReqDTO, stock);
        int count = stockMapper.insert(stock);
        if(count != 1){
            throw new MyException(GlobalResultEnum.FAIL.getCode(), GlobalResultEnum.FAIL.getMsg());
        }
    }

    /**
     * 通过id获取联系人和车辆数据
     * @param consignDTO
     */
    private void setConsignData(ConsignDTO consignDTO){
        Long consignorId = consignDTO.getConsignorId();
        Long consigneeId = consignDTO.getConsigneeId();
        Long vehicleId = consignDTO.getVehicleId();
        ContactsReqDTO contactsReqDTO = new ContactsReqDTO();
        contactsReqDTO.setId(consignorId);
        //设置发货人
        GlobalResultDTO consignorResult = contactsService.getContactsRecord(contactsReqDTO);
        if(consignorResult.isSuccess()){
            consignDTO.setConsignor((ContactsResDTO)consignorResult.getData());
        }
        contactsReqDTO.setId(consigneeId);
        //设置收货人
        GlobalResultDTO consigneeResult = contactsService.getContactsRecord(contactsReqDTO);
        if(consigneeResult.isSuccess()){
            consignDTO.setConsignee((ContactsResDTO)consigneeResult.getData());
        }
        //设置车辆信息
        VehicleReqDTO reqDTO = new VehicleReqDTO();
        reqDTO.setId(vehicleId);
        GlobalResultDTO vehicleResult = vehicleService.getVehicleRecord(reqDTO);
        if(vehicleResult.isSuccess()){
            BeanUtils.copyProperties((VehicleResDTO)vehicleResult.getData(), consignDTO);
        }
    }
}
