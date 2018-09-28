package com.dlf.model.dto.tms;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class ProductReqDTO {
    private String name;
    private String type;
    private String packaging;
    private Integer volume;
    private Integer weight;
    private BigDecimal price;
    private Integer length;
    private Integer width;
    private Integer height;
    private String remarks;

    public void setProductFromConsign(ConsignReqDTO reqDTO){
        this.name = reqDTO.getProductName();
        this.type = reqDTO.getProductType();
        this.packaging = reqDTO.getProductPackaging();
        this.volume = reqDTO.getProductVolume();
        this.weight = reqDTO.getProductWeight();
        this.price = reqDTO.getProductPrice();
        this.length = reqDTO.getProductLength();
        this.width = reqDTO.getProductWidth();
        this.height = reqDTO.getProductHeight();
        this.remarks = reqDTO.getProductRemarks();
    }
}
