package com.dlf.web.controller.tms;

import com.dlf.business.manager.tms.ConsignService;
import com.dlf.business.manager.tms.StockService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.tms.ConsignReqDTO;
import com.dlf.model.dto.tms.ConsignSearchDTO;
import com.dlf.model.dto.tms.StockSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    StockService stockService;

    /**
     * 分页查询
     * @param searchDTO
     * @return
     */
    @RequestMapping(value = "/queryPageByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryPageByParams(@RequestBody StockSearchDTO searchDTO) {
        return stockService.queryPageByParams(searchDTO);
    }
}
