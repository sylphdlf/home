package com.dlf.model.dto.tms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactsReqDTO {

    private Long id;

    private String code;

    private String name;

    private Integer type;

    private String telephone;

    private String mobile;

    private String address;

    private String accountBank;

    private String accountCard;

    private String accountName;

    private Integer invoiceType;

    private String oftenSend;

    private String oftenPackage;

    private String payType;

    private String remarks;
}
