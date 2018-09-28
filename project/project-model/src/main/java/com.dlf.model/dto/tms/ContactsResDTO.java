package com.dlf.model.dto.tms;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ContactsResDTO {

    private String name;

    private String telephone;

    private String mobile;

    private String address;
}
