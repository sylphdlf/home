package com.dlf.model.mapper;

import com.dlf.model.po.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper2 extends ProductMapper{
    /**
     * 新增并返回id
     * @param product
     * @return
     */
    int insertWithIdReturn(Product product);
}